package hiberspring.service.impl;

import hiberspring.domain.dtos.ProductSeedRootDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.service.ProductService;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static hiberspring.common.GlobalConstants.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final XmlParser xmlParser;
    private final BranchRepository branchRepository;

    public ProductServiceImpl(ValidationUtil validationUtil, ModelMapper modelMapper, ProductRepository productRepository, XmlParser xmlParser, BranchRepository branchRepository) {
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.xmlParser = xmlParser;
        this.branchRepository = branchRepository;
    }

    @Override
    public Boolean productsAreImported() {
        return this.productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return Files.readString(Path.of(PRODUCTS_FILE_PATH));
    }

    @Override
    public String importProducts() throws JAXBException, FileNotFoundException {
        StringBuilder result = new StringBuilder();

        ProductSeedRootDto productSeedRootDto = this.xmlParser.convertFromFile(PRODUCTS_FILE_PATH, ProductSeedRootDto.class);

        productSeedRootDto.getProducts().stream().forEach(productSeedDto -> {

            if(this.validationUtil.isValid(productSeedDto)){
                if(this.productRepository.findByName(productSeedDto.getName()) == null){
                    if(this.branchRepository.findByName(productSeedDto.getBranch()) != null){
                        Product product = this.modelMapper.map(productSeedDto, Product.class);

                        Branch branch = this.branchRepository.findByName(productSeedDto.getBranch());

                        product.setBranch(branch);

                        this.productRepository.saveAndFlush(product);

                        result.append(String.format(SUCCESSFUL_IMPORT_MESSAGE, product.getClass().getSimpleName(), product.getName()));

                    }else {
                        result.append("Brach does not exist!");
                    }


                }else {
                    result.append("Product is already in Database!");
                }


            }else {
                result.append(INCORRECT_DATA_MESSAGE);
            }
            result.append(System.lineSeparator());

        });






        return result.toString().trim();
    }
}
