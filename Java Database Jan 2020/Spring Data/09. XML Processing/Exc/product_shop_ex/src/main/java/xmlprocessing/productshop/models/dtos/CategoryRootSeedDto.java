package xmlprocessing.productshop.models.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryRootSeedDto {

    @XmlAttribute(name = "category")
    private List<CategorySeedDto> categories;

    public CategoryRootSeedDto() {
    }

    public List<CategorySeedDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategorySeedDto> categories) {
        this.categories = categories;
    }
}
