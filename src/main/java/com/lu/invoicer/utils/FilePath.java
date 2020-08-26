package com.lu.invoicer.utils;

import com.lu.invoicer.utils.enums.FileTypes;

import java.io.File;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class FilePath {

  public String subFolderPath;
  public String uuid;
  public FileTypes fileType;

  public FilePath(String userId, FileTypes filetype){
    subFolderPath = userId + File.separator + getFormattedDate();
    // New UUID for storing file
    uuid = UUID.randomUUID().toString();
    this.fileType = filetype;
  }

  public String full() {
    return "fileUploadDir" + File.separator + subFolderPath + File.separator + fileName();
  }
  public String partial() {
    return subFolderPath + File.separator + fileName();
  }
  public String fileName() {
    return uuid+"."+fileType.small;
  }

  public String getSubFolderPath() {
    return subFolderPath;
  }

  public void setSubFolderPath(String subFolderPath) {
    this.subFolderPath = subFolderPath;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  private String getFormattedDate(){
    ZonedDateTime now = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
    return now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
  }
}
