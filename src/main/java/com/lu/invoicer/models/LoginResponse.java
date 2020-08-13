package com.lu.invoicer.models;

public class LoginResponse extends ApiResponse {

  private String token;

  public LoginResponse() {
  }

  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
