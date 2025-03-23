package com.mvc.crud.validator;


import com.mvc.crud.repository.MembersRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueCustomValidator implements ConstraintValidator<UniqueCustom, String> {
    @Autowired
    private MembersRepository MembersRepository;

    @Override
    public boolean isValid(String user_id, ConstraintValidatorContext context){
        if(user_id == null || user_id.isEmpty()){
            return false;
        }
        return !MembersRepository.existsByUserId(user_id);
    }
}
