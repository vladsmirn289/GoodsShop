package com.shop.GoodsShop.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidateUtil {
    public static final Logger logger = LoggerFactory.getLogger(ValidateUtil.class);

    public static Map<String, String> validate(BindingResult bindingResult) {
        logger.info("Called validate method");
        Map<String, String> errors = null;
        if (bindingResult.hasErrors()) {
            logger.warn("Binding result has errors");
            errors = bindingResult
                        .getFieldErrors()
                        .stream()
                        .peek(b -> logger.debug(b.getField() + "Error: " + b.getDefaultMessage()))
                        .collect(Collectors.toMap(
                                fieldError -> fieldError.getField() + "Error",
                                FieldError::getDefaultMessage));
        } else {
            logger.info("Errors not found");
            errors = new HashMap<>();
        }

        return errors;
    }

    public static <T> Map<String, String> validate(Set<ConstraintViolation<T>> violations) {
        logger.info("Called validate method");
        return violations.stream()
                .peek(v -> logger.debug(v.getPropertyPath() + "Error: " + v.getMessage()))
                .collect(Collectors.toMap(
                        v -> v.getPropertyPath() + "Error",
                        ConstraintViolation::getMessage
                ));
    }
}
