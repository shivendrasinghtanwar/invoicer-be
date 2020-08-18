package com.lu.invoicer.models.invoices;

import java.time.Instant;
import java.util.ArrayList;

public class InvoiceData {
  private String payerName;
  private String payerEmail;
  private Instant dueDate;
  private ArrayList<InvoiceProduct> products;
  private String footer;
  private Double total;
  private String freeTextOne;
  private String freeTextTwo;
  private Integer templateNumber;


  public String getPayerName() {
    return payerName;
  }

  public void setPayerName(String payerName) {
    this.payerName = payerName;
  }

  public String getPayerEmail() {
    return payerEmail;
  }

  public void setPayerEmail(String payerEmail) {
    this.payerEmail = payerEmail;
  }

  public Instant getDueDate() {
    return dueDate;
  }

  public void setDueDate(Instant dueDate) {
    this.dueDate = dueDate;
  }

  public ArrayList<InvoiceProduct> getProducts() {
    return products;
  }

  public void setProducts(ArrayList<InvoiceProduct> products) {
    this.products = products;
  }

  public String getFooter() {
    return footer;
  }

  public void setFooter(String footer) {
    this.footer = footer;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public String getFreeTextOne() {
    return freeTextOne;
  }

  public void setFreeTextOne(String freeTextOne) {
    this.freeTextOne = freeTextOne;
  }

  public String getFreeTextTwo() {
    return freeTextTwo;
  }

  public void setFreeTextTwo(String freeTextTwo) {
    this.freeTextTwo = freeTextTwo;
  }


  public Integer getTemplateNumber() {
    return templateNumber;
  }

  public void setTemplateNumber(Integer templateNumber) {
    this.templateNumber = templateNumber;
  }
}
