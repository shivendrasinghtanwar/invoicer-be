package com.lu.invoicer.controllers;


import com.lu.invoicer.models.StringApiResponse;
import com.lu.invoicer.models.billers.Biller;
import com.lu.invoicer.models.billers.BillerDetails;
import com.lu.invoicer.models.invoices.Invoice;
import com.lu.invoicer.models.invoices.InvoiceData;
import com.lu.invoicer.repos.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class InvoiceController {

  @Autowired
  InvoiceRepository invoiceRepository;

  @PostMapping(value = "/invoice")
  public Invoice add(@RequestBody InvoiceData invoiceData) {
    Authentication authenticator = SecurityContextHolder.getContext().getAuthentication();
    BillerDetails biller = (BillerDetails)authenticator.getPrincipal();
    Invoice invoice = new Invoice(invoiceData,biller.getId());

    return invoiceRepository.save(invoice);
  }

  @GetMapping(value = "/invoice")
  public List<Invoice> getAll() {
    return invoiceRepository.findAll();
  }

  @GetMapping(value = "/invoice/number/new")
  public Map<String,String> getNewInvoiceNumber() {
    return new StringApiResponse("INV"+String.format("%05d",invoiceRepository.count()+1)).toMap();
  }

}
