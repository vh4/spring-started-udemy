package com.mvc.customer;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CustomValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;

        // Trim the 'name' field
        if (customer.getName() != null) {
            customer.setName(customer.getName().trim());
        }

        // Check if the first character of name is uppercase
        if (customer.getName() != null && !customer.getName().isEmpty()) {
            char firstChar = customer.getName().charAt(0);
            if (!Character.isUpperCase(firstChar)) {
                errors.rejectValue(
                        "name",
                        "name.firstUpperCase",
                        "The first character of the name must be uppercase"
                );
            }
        }
    }
}
