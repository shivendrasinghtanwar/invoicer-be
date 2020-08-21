package com.lu.invoicer.models.invoices;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("invoice")
public class Invoice {


  @Id
  private String id;
  private Instant createdAt;
  private String createdBy;
  private Double total;
  private InvoiceStatus status;
  private Integer statusCode;
  private InvoiceData data;

  public Invoice(InvoiceData data, String createdBy){
    this.data = data;
    this.createdBy = createdBy;
    this.createdAt = Instant.now();
  }

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


  public InvoiceStatus getStatus() {
    return status;
  }

  public void setStatus(InvoiceStatus status) {
    this.status = status;
    setStatusCode(status.getStatusCode());
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  private void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }
}
