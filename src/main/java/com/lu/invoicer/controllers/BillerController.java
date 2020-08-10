package com.lu.invoicer.controllers;

import com.lu.invoicer.models.Biller;
import com.lu.invoicer.models.BillerLoginRequest;
import com.lu.invoicer.models.LoginResponse;
import com.lu.invoicer.repos.BillerRepository;
import com.lu.invoicer.utils.JavaTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillerController {


  @Autowired
  private BillerRepository billerRepository;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JavaTokenUtils jwtTokenUtil;

  @GetMapping(value = "/test")
  public int test(){
    System.out.println("inside");
    return 2;
  }

  @PostMapping(value = "/api/biller")
  public Biller add(@RequestBody Biller biller) {
    biller.setPassword(bCryptPasswordEncoder.encode(biller.getPassword()));
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

  @PostMapping(value = "/api/biller/login")
  public ResponseEntity<LoginResponse> login(@RequestBody BillerLoginRequest loginRequest) throws Exception{//    authenticate(loginRequest.getUsername(),loginRequest.getPassword());
    System.out.println(loginRequest.getUsername());
    try{
      Biller biller = billerRepository.findByEmail(loginRequest.getUsername());
      System.out.println(bCryptPasswordEncoder.matches(loginRequest.getPassword(), biller.getPassword()));
      if(bCryptPasswordEncoder.matches(loginRequest.getPassword(), biller.getPassword()))
      {
        System.out.println("yaha");
        try{
          String token = jwtTokenUtil.generateToken(biller.getId());
          return ResponseEntity.ok(new LoginResponse(token));
        }catch (Exception e){
          e.printStackTrace();
          throw e;
        }


      }else {
        System.out.println("here");
//        return new LoginResponse();
        throw new Exception("Username or password wrong");
      }
    }catch (Exception e){
      throw new Exception("Username or password wrong");
    }

  }
}
