package com.xmlproductshop.dtos;

import com.xmlproductshop.entities.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductViewDto {
    @XmlAttribute
    private String name;
    @XmlElement
    private BigDecimal price;
    @XmlAttribute
    private User seller;

    public ProductViewDto() {
    }

    @Length(min = 3,message = "Wrong size")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
