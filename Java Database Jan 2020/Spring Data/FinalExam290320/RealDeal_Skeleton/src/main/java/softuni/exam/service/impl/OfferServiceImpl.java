package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.OfferSeedRootDto;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Offer;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static softuni.exam.constants.GlobalConstants.OFFERS_FILE_PATH;

@Service
@Transactional
public class OfferServiceImpl implements OfferService {


    private final OfferRepository offerRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;

    public OfferServiceImpl(OfferRepository offerRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, CarRepository carRepository, SellerRepository sellerRepository) {
        this.offerRepository = offerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return true;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(OFFERS_FILE_PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder result = new StringBuilder();

        OfferSeedRootDto offerSeedRootDto = this.xmlParser.convertFromFile(OFFERS_FILE_PATH, OfferSeedRootDto.class);

        offerSeedRootDto.getOffers().stream().forEach(offerSeedDto -> {
            if(this.validationUtil.isValid(offerSeedDto)){

                LocalDateTime addedOn = LocalDateTime.parse(offerSeedDto.getAddedOn(),
                        DateTimeFormatter.ofPattern("yyyy-M-d HH:mm:ss"));

                if(this.offerRepository.findByDescriptionAndAddedOn(offerSeedDto.getDescription(),
                        addedOn) == null){


                    Offer offer = this.modelMapper.map(offerSeedDto, Offer.class);

                    offer.setAddedOn(addedOn);

                    Car car = this.carRepository.findById(offerSeedDto.getCarId());

                    Seller seller = this.sellerRepository.findById(offerSeedDto.getSellerId());

                    offer.setCar(car);
                    offer.setSeller(seller);

                    this.offerRepository.saveAndFlush(offer);

                    result.append(String.format("Successfully import offer %s - %s",
                            offerSeedDto.getAddedOn(), offerSeedDto.isHasGoldStatus()));

                }else {
                    result.append("Offer already in Database!");
                }


            }else {
                result.append("Invalid offer");
            }





        });






        return result.toString().trim();
    }
}
