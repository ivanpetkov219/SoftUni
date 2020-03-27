package com.softuni.xmlparsingexerciseone.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLParserImpl implements XMLParser {
    private JAXBContext jaxbContext;
    @Override
    public <T> void write(T object,String pathName) throws JAXBException {


            File file=new File(pathName);
            jaxbContext=JAXBContext.newInstance(object.getClass());

            Marshaller marshaller=jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

            marshaller.marshal(object,file);



    }

    @Override
    public <T> T read( Class objectClass,String pathName) throws JAXBException {

            File file =new File(pathName);
            this.jaxbContext=JAXBContext.newInstance(objectClass);
            Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();

            T result=(T)unmarshaller.unmarshal(file);

            return result;






    }
}
