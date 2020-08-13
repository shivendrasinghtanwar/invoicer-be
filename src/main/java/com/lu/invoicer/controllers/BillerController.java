package com.lu.invoicer.controllers;

import com.lu.invoicer.models.Biller;
import com.lu.invoicer.models.BillerLoginRequest;
import com.lu.invoicer.models.LoginResponse;
import com.lu.invoicer.repos.BillerRepository;
import com.lu.invoicer.utils.JavaTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillerController {


  @Autowired
  private BillerRepository billerRepository;
  @Autowired
  private PasswordEncoder bCryptPasswordEncoder;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JavaTokenUtils jwtTokenUtil;

  @GetMapping(value = "/ping")
  public String ping(){
    System.out.println("ping called");
    return "pong";
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
  public ResponseEntity<?> login(@RequestBody BillerLoginRequest loginRequest) throws Exception {

    try{
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
      );
    }catch (Exception e){
      throw e;
    }

    Biller biller = billerRepository.findByEmail(loginRequest.getUsername());
    String token = jwtTokenUtil.generateToken(biller.getEmail());
    LoginResponse response = new LoginResponse();
    response.setToken(token);
    return ResponseEntity.ok(response);

  }
/*  public ResponseEntity<?> login(@RequestBody BillerLoginRequest loginRequest) {
    String usernameOrPasswordIncorrect = "Username or password wrong";
    ResponseEntity response;
    HashMap responseBody = new HashMap<String,String>();
    try{
      Biller biller = billerRepository.findByEmail(loginRequest.getUsername());
      if(biller!=null){
        if(bCryptPasswordEncoder.matches(loginRequest.getPassword(), biller.getPassword()))
        {
          String token = jwtTokenUtil.generateToken(biller.getId());
          responseBody.put("token",token);
          response = new ResponseEntity(responseBody,HttpStatus.OK);
        }else {
          throw new Exception(usernameOrPasswordIncorrect);
        }
      }else{
        throw new Exception(usernameOrPasswordIncorrect);
      }
    }catch (Exception e){
      System.out.println(e.getMessage());
      responseBody.put("error",usernameOrPasswordIncorrect);
      response = new ResponseEntity(responseBody,HttpStatus.FORBIDDEN);
    }
    return response;
  }*/
}
