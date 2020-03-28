package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.domain.dtos.PictureSeedRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;


import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static softuni.exam.constants.GlobalConstants.PICTURES_FILE_PATH;


@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    public PictureServiceImpl(PictureRepository pictureRepository, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser) {
        this.pictureRepository = pictureRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }


    @Override
    public String importPictures() throws IOException, JAXBException {
        StringBuilder result = new StringBuilder();

        PictureSeedRootDto pictureSeedRootDtos = this.xmlParser
                .convertFromFile(PICTURES_FILE_PATH, PictureSeedRootDto.class);



       pictureSeedRootDtos.getPictures().forEach(pictureSeedDto -> {
            if(this.validationUtil.isValid(pictureSeedDto)){
                if(this.pictureRepository.findByUrl(pictureSeedDto.getUrl()) == null){
                    Picture picture = this.modelMapper.map(pictureSeedDto, Picture.class);

                    result.append(String.format("Successfully imported picture - %s", pictureSeedDto.getUrl()));

                    this.pictureRepository.saveAndFlush(picture);

                }else {
                    result.append("Picture already exists in the database!");
                }
            }else {
                result.append("Invalid picture!");
            }

            result.append(System.lineSeparator());
        });

       return result.toString().trim();
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {

        return Files.readString(Path.of(PICTURES_FILE_PATH));
    }

    @Override
    public Picture getPictureByUrl(String url) {
        return this.pictureRepository.findByUrl(url);
    }


}
