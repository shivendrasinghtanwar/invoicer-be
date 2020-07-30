package com.lu.invoicer.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "biller")
public class Biller {
  @Id
  private String id;

  public FullName getName() {
    return name;
  }

  public void setName(FullName name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  private FullName name;

  @Indexed(unique = true)
  private String email;

  private Address address;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
