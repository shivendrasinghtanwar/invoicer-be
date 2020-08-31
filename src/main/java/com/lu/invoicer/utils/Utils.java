package com.lu.invoicer.utils;

import com.lu.invoicer.InvoicerApplication;
import org.apache.commons.io.IOUtils;


import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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

  public static String getResourceString(String fileName) throws Exception {
    return IOUtils.toString(getResource(fileName), StandardCharsets.UTF_8.name());
  }
}
