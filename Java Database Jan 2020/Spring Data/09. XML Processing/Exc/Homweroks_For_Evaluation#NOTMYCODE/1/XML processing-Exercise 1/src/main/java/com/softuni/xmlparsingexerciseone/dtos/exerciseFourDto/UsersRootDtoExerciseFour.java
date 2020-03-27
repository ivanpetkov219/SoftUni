package com.softuni.xmlparsingexerciseone.dtos.exerciseFourDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersRootDtoExerciseFour {

    @XmlAttribute(name="count")
    private int count;

    @XmlElement(name="user")
    private List<UserDtoExFour> users;
}
