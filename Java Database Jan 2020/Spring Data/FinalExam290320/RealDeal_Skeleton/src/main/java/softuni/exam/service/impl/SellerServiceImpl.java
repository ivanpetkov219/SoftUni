package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.models.dtos.SellerSeedRoodDto;
import softuni.exam.models.entities.Rating;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.Enum.valueOf;
import static softuni.exam.constants.GlobalConstants.SELLERS_FILE_PATH;
import static softuni.exam.models.entities.Rating.UNKNOWN;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public SellerServiceImpl(SellerRepository sellerRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.sellerRepository = sellerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Path.of(SELLERS_FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder result = new StringBuilder();

        SellerSeedRoodDto sellerSeedRoodDto = this.xmlParser.convertFromFile(SELLERS_FILE_PATH, SellerSeedRoodDto.class);

        sellerSeedRoodDto.getSellers().stream().forEach(sellerSeedDto -> {
            if(this.validationUtil.isValid(sellerSeedDto)){
                if(this.sellerRepository.findByEmail(sellerSeedDto.getEmail()) == null){
                    Seller seller = this.modelMapper.map(sellerSeedDto, Seller.class);

                    if(sellerSeedDto.getRating() == null){
                        seller.setRating(UNKNOWN);
                    }else {

                        seller.setRating(valueOf(Rating.class, sellerSeedDto.getRating()));
                    }

                    this.sellerRepository.saveAndFlush(seller);
                    result.append(String.format("Successfully import seller %s - %s",
                            sellerSeedDto.getLastName(),
                            sellerSeedDto.getEmail()));
                }else {
                    result.append("Seller already exists in database!");
                }


            }else {
                result.append("Invalid seller");
            }


            result.append(System.lineSeparator());

        });










        return result.toString().trim();
    }
}
