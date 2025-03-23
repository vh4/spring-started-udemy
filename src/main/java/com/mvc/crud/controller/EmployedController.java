package com.mvc.crud.controller;

import com.mvc.crud.entity.Employed;
import com.mvc.crud.services.EmployedService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class EmployedController {

    private final EmployedService service;

    public EmployedController(EmployedService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("employees", service.findAll());
        model.addAttribute("newEmployee", new Employed());
        return "employee-list";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("newEmployee") Employed employed, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessages", result.getAllErrors());
            model.addAttribute("employees", service.findAll());
            return "employee-list";
        }
        service.save(employed);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    @ResponseBody
    public Employed getEmployee(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("editEmployee") Employed employed, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessages", result.getAllErrors());
            model.addAttribute("employees", service.findAll());
            return "employee-list";
        }
        employed.setId(id);
        service.updated(employed);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "success";
    }
}
