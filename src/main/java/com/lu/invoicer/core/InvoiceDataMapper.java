package com.lu.invoicer.core;

import com.lu.invoicer.utils.Utils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

public class InvoiceDataMapper {
  private String invoiceTemplateDir = "templates/invoices";
  private String templateString = "";
  private SimpleDateFormat sdf = new SimpleDateFormat();


  public String fillTemplate(){
    return "";
  }

  public String getTemplateString() throws Exception {
    return IOUtils.toString(getTemplateStream(), StandardCharsets.UTF_8.name());
  }

  public InputStream getTemplateStream() throws Exception {
    return Utils.getResource(invoiceTemplateDir+ File.separator+ "index.html");
  }
}
