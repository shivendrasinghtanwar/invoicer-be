package com.lu.invoicer.models.invoices;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collation = "invoices")
public class Invoice {


  @Id
  private String id;
  private Instant createdAt;
  private String createdBy;
  private Double total;
  private InvoiceData data;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public InvoiceData getData() {
    return data;
  }

  public void setData(InvoiceData data) {
    this.data = data;
  }


}
