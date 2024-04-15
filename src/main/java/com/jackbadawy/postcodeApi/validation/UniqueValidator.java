package com.jackbadawy.postcodeApi.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.jackbadawy.postcodeApi.posts.PostCodeItemRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, String> {
	@Autowired
    private PostCodeItemRepository repository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return !repository.existsByPostcodeOrSuburb(value, value);
    }
}
