package com.lu.invoicer.controllers;

import com.lu.invoicer.models.billers.Biller;
import com.lu.invoicer.models.billers.BillerDetails;
import com.lu.invoicer.models.billers.BillerInfo;
import com.lu.invoicer.models.billers.BillerLoginRequest;
import com.lu.invoicer.models.LoginResponse;
import com.lu.invoicer.repos.BillerRepository;
import com.lu.invoicer.utils.JavaTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

  @GetMapping(value = "/biller/info")
  public BillerInfo getBillerInfo() {
    Authentication authenticator = SecurityContextHolder.getContext().getAuthentication();
    BillerDetails biller = (BillerDetails)authenticator.getPrincipal();
    Optional<Biller> optionalBiller = billerRepository.findById(biller.getId());
    if(optionalBiller.isEmpty()) {
      throw new NoSuchElementException("Biller not found");
    }
    else {
      return new BillerInfo(optionalBiller.get());
    }

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
}
