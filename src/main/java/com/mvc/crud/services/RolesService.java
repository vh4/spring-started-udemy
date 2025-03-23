package com.mvc.crud.services;

import com.mvc.crud.entity.Roles;
import com.mvc.crud.repository.RolesRepository;
import org.springframework.stereotype.Service;

@Service
public class RolesService {
    private final RolesRepository rolesRepository;

    public RolesService(RolesRepository role){
        this.rolesRepository = role;
    }

    public void save(Roles roles){
        rolesRepository.save(roles);
    }
}