package softuni.exam.domain.dtos;


import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Position;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PlayerSeedDto {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer number;
    @Expose
    private BigDecimal salary;
    @Expose
    private Position position;
    @Expose
    private PictureSeedDto pictureSeedDto;
    @Expose
    private TeamSeedDto teamSeedDto;

    public PlayerSeedDto() {
    }

    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Length(min = 3, max = 20)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Min(value = 1)
    @Max(value = 99)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @NotNull
    @DecimalMin(value = "0")
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @NotNull
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @NotNull
    public PictureSeedDto getPictureSeedDto() {
        return pictureSeedDto;
    }

    public void setPictureSeedDto(PictureSeedDto pictureSeedDto) {
        this.pictureSeedDto = pictureSeedDto;
    }
    @NotNull
    public TeamSeedDto getTeamSeedDto() {
        return teamSeedDto;
    }

    public void setTeamSeedDto(TeamSeedDto teamSeedDto) {
        this.teamSeedDto = teamSeedDto;
    }
}
