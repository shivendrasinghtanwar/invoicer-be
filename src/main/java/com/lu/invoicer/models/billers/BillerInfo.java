package com.lu.invoicer.models.billers;


public class BillerInfo {
  private FullName name;
  private String email;
  private Address address;

  public FullName getName() {
    return name;
  }

  public void setName(FullName name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public BillerInfo(Biller biller){
    this.name = biller.getName();
    this.email = biller.getEmail();
    this.address = biller.getAddress();
  }
}
