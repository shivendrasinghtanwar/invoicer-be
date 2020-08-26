package com.lu.invoicer.utils;

import com.lu.invoicer.InvoicerApplication;

import java.io.File;
import java.io.InputStream;

public class Utils {
  /**
   * returns null if file does not exist
   * @return
   */
  public static InputStream getResource(String fileName) throws Exception {
    try{
      return InvoicerApplication.class.getResourceAsStream(File.separator+fileName);
    }catch (Exception e){
      e.printStackTrace();
      throw e;
    }
  }
}
