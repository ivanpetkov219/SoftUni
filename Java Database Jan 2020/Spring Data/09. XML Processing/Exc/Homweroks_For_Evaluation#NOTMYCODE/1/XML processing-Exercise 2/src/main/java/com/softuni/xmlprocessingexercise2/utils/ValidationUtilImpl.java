package com.softuni.xmlprocessingexercise2.utils;

import com.softuni.xmlprocessingexercise2.utils.base.ValidationUtil;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

@Component
public class ValidationUtilImpl implements ValidationUtil {

    private javax.validation.Validator validator;

    public ValidationUtilImpl() {
        this.validator =  Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <T> boolean isValid(T entity) {
        return this.validator.validate(entity).isEmpty();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> getViolations(T entity) {
        return this.validator.validate(entity);
    }
}
