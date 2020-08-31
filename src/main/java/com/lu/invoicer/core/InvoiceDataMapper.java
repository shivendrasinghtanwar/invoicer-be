package com.lu.invoicer.core;

import com.lu.invoicer.utils.FilePath;
import com.lu.invoicer.utils.Utils;
import com.lu.invoicer.utils.enums.FileTypes;
import com.mongodb.annotations.Beta;
import org.apache.commons.io.IOUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;


@Component
public class InvoiceDataMapper {
  private String invoiceTemplateDir = "templates/invoices";
  private SimpleDateFormat sdf = new SimpleDateFormat();


  public String fillTemplate() throws Exception {

    String templateString = "";
    templateString = getTemplateString();
    templateString = templateString.replace("$payerName$","Shivendra");

    return saveTempFile(templateString);
  }

  public String saveTempFile(String htmlString) throws IOException {
    HtmlCleaner htmlCleaner = new HtmlCleaner();
    TagNode cleanedHtmlNode = htmlCleaner.clean(htmlString);
    CleanerProperties props = new CleanerProperties();

    // set some properties to non-default values
    props.setTranslateSpecialEntities(true);
    props.setTransResCharsToNCR(true);
    props.setOmitComments(true);

    // serialize to temporary html file
    FilePath filePath = new FilePath("temp");
    filePath.setFileType(FileTypes.HTML);
    String tempFileFullPath = filePath.full();

    File file = new File(filePath.getFileUploadsDir()+filePath.getSubFolderPath());

    boolean dirCreated = file.exists();

    if(!dirCreated) dirCreated = file.mkdirs();

    if(dirCreated){
      PrettyXmlSerializer xmlSerializer = new PrettyXmlSerializer(props);
      xmlSerializer.writeToFile(
        cleanedHtmlNode, tempFileFullPath, StandardCharsets.UTF_8.name()
      );
    }


    return tempFileFullPath;
  }

  public String getTemplateString() throws Exception {
    return IOUtils.toString(getTemplateStream(), StandardCharsets.UTF_8.name());
  }

  public InputStream getTemplateStream() throws Exception {
    return Utils.getResource(invoiceTemplateDir+ File.separator+ "index.html");
  }
}
