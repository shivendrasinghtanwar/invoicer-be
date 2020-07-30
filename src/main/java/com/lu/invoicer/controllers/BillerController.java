package com.lu.invoicer.controllers;

import com.lu.invoicer.models.Biller;
import com.lu.invoicer.repos.BillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillerController {

  @Autowired
  private BillerRepository billerRepository;

  @GetMapping(value = "/test")
  public int test(){
    System.out.println("inside");
    return 2;
  }

  @PostMapping(value = "/biller")
  public Biller add(@RequestBody Biller biller) {
    return billerRepository.save(biller);
  }

  @GetMapping(value = "/biller")
  public List<Biller> getAll() {
    return billerRepository.findAll();
  }

  @GetMapping(value = "/biller/{id}")
  public Biller getOne(@PathVariable String id) {
    return billerRepository.findById(id).orElseThrow();
  }

  @DeleteMapping(value = "/biller/{id}")
  public String deleteOne(@PathVariable String id){
    billerRepository.deleteById(id);
    return "";
  }
}
