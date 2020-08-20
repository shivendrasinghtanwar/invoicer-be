package com.lu.invoicer.models;

import java.util.HashMap;
import java.util.Map;

public class StringApiResponse {
  private String key="data";
  private String value;

  public StringApiResponse(){
    this.value="";
    this.key="";
  }

  public StringApiResponse(String value){
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Map<String, String> toMap(){
    Map<String,String> res = new HashMap<String,String>();
    res.put(this.key,this.value);
    return res;
  }
}
