package com.mvc.crud.services;

import com.mvc.crud.entity.Members;
import com.mvc.crud.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MembersRepository repository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MembersRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(Members members) {
        String encodedPassword = passwordEncoder.encode(members.getPassword());
        members.setPassword(encodedPassword);
        repository.save(members);
    }
}