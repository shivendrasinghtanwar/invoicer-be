package com.lu.invoicer.service;

import com.lu.invoicer.models.billers.Biller;
import com.lu.invoicer.repos.BillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BillerDataService implements UserDetailsService {

  @Autowired
  BillerRepository billerRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Biller biller = billerRepository.findByEmail(email);

    if(biller!=null){
      return new User(biller.getEmail(),biller.getPassword(),new ArrayList<>());
    }else{
      throw new UsernameNotFoundException("User not found");
    }
  }
}
