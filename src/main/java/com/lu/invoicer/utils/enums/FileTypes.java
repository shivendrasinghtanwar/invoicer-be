package com.lu.invoicer.utils.enums;

public enum FileTypes {
  PDF("pdf"),
  XML("xml"),
  HTML("html");

  public String small;

  public String getType() {
    return small;
  }

  public void setType(String small) {
    this.small = small;
  }

  FileTypes(String small){
    this.small = small;
  }

}
