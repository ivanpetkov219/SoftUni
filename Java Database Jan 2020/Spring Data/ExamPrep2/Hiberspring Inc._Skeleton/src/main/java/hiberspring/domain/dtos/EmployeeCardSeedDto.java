package hiberspring.domain.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;

public class EmployeeCardSeedDto {
    @Expose
    private String number;

    public EmployeeCardSeedDto() {
    }

    @NotNull
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
