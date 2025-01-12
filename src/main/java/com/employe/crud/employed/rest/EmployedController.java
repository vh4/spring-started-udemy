package com.employe.crud.employed.rest;

import com.employe.crud.employed.entity.Employed;
import com.employe.crud.employed.services.EmployedServiceDao;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployedController extends RuntimeException {
    private final EmployedServiceDao employedServiceDao;

    public EmployedController(EmployedServiceDao employedServiceDos){
        this.employedServiceDao = employedServiceDos;
    }

    @GetMapping("/employed")
    public List<Employed> index(){
        return this.employedServiceDao.findAll();
    }

    @GetMapping("/employed/{id}")
    public Employed find(@PathVariable int id){
        return this.employedServiceDao.findBy(id);
    }

    @PostMapping("/employed")
    public Employed create(@RequestBody Employed employed){
        this.employedServiceDao.save(employed);
        return this.employedServiceDao.findBy(employed.getId());
    }

    @PutMapping("/employed/{id}")
    public Employed update(@RequestBody Employed employed, @PathVariable int id){

        Employed employed1 = this.employedServiceDao.findBy(id);
        employed1.setFirst_name(employed.getFirst_name());
        employed1.setLast_name(employed.getLast_name());
        employed1.setEmail(employed.getEmail());
        employed1.setPhone(employed.getPhone());
        employed1.setPicture(employed.getPicture());
        employed1.setCity(employed.getCity());
        this.employedServiceDao.Updated(employed1);
        return this.employedServiceDao.findBy(id);

    }

    @DeleteMapping("/employed/{id}")
    public String delete(@PathVariable int id){
        this.employedServiceDao.deleteBy(id);
        return "Employed " + id + " is deleted!";
    }

}
