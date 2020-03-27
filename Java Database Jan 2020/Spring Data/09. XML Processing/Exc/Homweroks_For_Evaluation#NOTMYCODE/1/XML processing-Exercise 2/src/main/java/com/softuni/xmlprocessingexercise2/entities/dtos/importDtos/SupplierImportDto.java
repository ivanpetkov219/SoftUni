package com.softuni.xmlprocessingexercise2.entities.dtos.importDtos;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierImportDto {

    @XmlAttribute(name="name")
    private String name;

    @XmlAttribute(name="is-importer")
    private boolean isImporter;

    public SupplierImportDto(String name, boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    public SupplierImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
