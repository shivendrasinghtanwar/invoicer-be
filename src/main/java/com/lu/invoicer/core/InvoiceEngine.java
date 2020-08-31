package com.lu.invoicer.core;

import org.springframework.beans.factory.annotation.Autowired;

public class InvoiceEngine {

  @Autowired
  private InvoiceDataMapper dataMapper;

  public void generate() throws Exception {
    dataMapper.getTemplateString();
  }

}
