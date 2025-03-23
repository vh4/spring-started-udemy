package com.mvc.crud.controller;

import com.mvc.crud.entity.Members;
import com.mvc.crud.entity.Roles;
import com.mvc.crud.services.MemberService;
import com.mvc.crud.services.RolesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final MemberService memberService;
    private final RolesService rolesService;

    @Autowired
    public LoginController(MemberService memberService, RolesService rolesService) {
        this.memberService = memberService;
        this.rolesService = rolesService;
    }

    @GetMapping("/req/login")
    public String login() {
        return "login";
    }

    @GetMapping("/req/signup")
    public String signup(Model model) {
        model.addAttribute("member", new Members());
        return "signup";
    }

    @PostMapping("/req/signup")
    public String registerMember(@Valid Members member, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "signup";
        }

        memberService.save(member);

        Roles role = new Roles();
        role.setUserId(member.getUserId());
        role.setRole("ROLE_EMPLOYEE");
        rolesService.save(role);

        model.addAttribute("success", "Registration successful!");
        model.addAttribute("member", new Members());
        return "signup";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}