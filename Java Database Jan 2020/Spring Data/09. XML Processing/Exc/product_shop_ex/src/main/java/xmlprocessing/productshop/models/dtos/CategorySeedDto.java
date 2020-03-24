package xmlprocessing.productshop.models.dtos;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedDto {

    @XmlAttribute(name = "name")
    private String name;

    public CategorySeedDto() {
    }

    @NotNull(message = "Name cannot be null!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
