package com.lu.invoicer.controllers;


import com.lu.invoicer.core.InvoiceDataMapper;
import com.lu.invoicer.models.StringApiResponse;
import com.lu.invoicer.models.billers.BillerDetails;
import com.lu.invoicer.models.invoices.Invoice;
import com.lu.invoicer.models.invoices.InvoiceData;
import com.lu.invoicer.models.invoices.InvoiceStatus;
import com.lu.invoicer.repos.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class InvoiceController {

  @Autowired
  InvoiceRepository invoiceRepository;
  @Autowired
  private Environment env;
  @GetMapping(value = "/api/test")
  public Map<String, String> test() throws Exception {
    InvoiceDataMapper invoiceDataMapper = new InvoiceDataMapper();
    String response= "";
    try{
      response = invoiceDataMapper.fillTemplate();
    }catch (Exception e){
      e.printStackTrace();
      throw e;
    }
    return new StringApiResponse(response).toMap();
  }

  @PostMapping(value = "/invoice")
  public Invoice add(@RequestBody InvoiceData invoiceData) {
    Authentication authenticator = SecurityContextHolder.getContext().getAuthentication();
    BillerDetails biller = (BillerDetails)authenticator.getPrincipal();
    Invoice invoice = new Invoice(invoiceData,biller.getId());
    invoice.setStatus(InvoiceStatus.DATA_SAVED);
    return invoiceRepository.save(invoice);
  }

  @GetMapping(value = "/invoice")
  public List<Invoice> getAll() {
    return invoiceRepository.findAll();
  }

  @GetMapping(value = "/invoice/{id}")
  public Invoice getOne(@PathVariable String id) {
    return invoiceRepository.findById(id).orElseThrow();
  }

  @GetMapping(value = "/invoice/number/new")
  public Map<String,String> getNewInvoiceNumber() {
    return new StringApiResponse("INV"+String.format("%05d",invoiceRepository.count()+1)).toMap();
  }

}
