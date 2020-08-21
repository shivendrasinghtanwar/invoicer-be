package com.lu.invoicer.models.billers;

import com.lu.invoicer.models.billers.Biller;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class BillerDetails implements UserDetails {
  private String username;
  private String password;
  private String id;
  private Boolean isEnabled;

  public BillerDetails(Biller biller){
    username = biller.getEmail();
    password = biller.getPassword();
    id = biller.getId();
    isEnabled = true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
