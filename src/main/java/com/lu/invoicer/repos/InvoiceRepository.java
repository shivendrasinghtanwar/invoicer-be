package com.lu.invoicer.repos;

import com.lu.invoicer.models.invoices.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceRepository extends MongoRepository<Invoice,String> {
}
