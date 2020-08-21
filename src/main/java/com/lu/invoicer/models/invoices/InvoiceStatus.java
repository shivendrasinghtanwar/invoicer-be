package com.lu.invoicer.models.invoices;

public enum InvoiceStatus {
  DATA_SAVED(1010),
  CHANNEL_SELECTED(1020),
  SENT(1030);

  public Integer getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  private Integer statusCode;
  InvoiceStatus(Integer statusCode){
    this.statusCode = statusCode;
  }

}
