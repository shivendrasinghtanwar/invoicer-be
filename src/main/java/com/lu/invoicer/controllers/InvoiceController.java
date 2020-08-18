package com.lu.invoicer.controllers;


import com.lu.invoicer.models.billers.Biller;
import com.lu.invoicer.models.invoices.Invoice;
import com.lu.invoicer.repos.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvoiceController {

  @Autowired
  InvoiceRepository invoiceRepository;

  @PostMapping(value = "/invoice")
  public Invoice add(@RequestBody Invoice invoice) {
    return invoiceRepository.save(invoice);
  }

  @GetMapping(value = "/invoice")
  public List<Invoice> getAll() {
    return invoiceRepository.findAll();
  }

}
