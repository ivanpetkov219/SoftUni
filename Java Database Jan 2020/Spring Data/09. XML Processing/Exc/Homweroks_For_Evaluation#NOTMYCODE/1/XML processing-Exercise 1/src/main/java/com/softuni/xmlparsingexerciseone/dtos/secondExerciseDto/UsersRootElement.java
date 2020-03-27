package com.softuni.xmlparsingexerciseone.dtos.secondExerciseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersRootElement {

    @XmlElement(name="user")
    private Set<UserDtoSecondExercise> users;


}
