package com.lu.invoicer.utils;

import com.lu.invoicer.utils.enums.FileTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class FilePath {

  @Autowired
  private Environment env;

  private String userId;
  private String subFolderPath;
  private String uuid;
  private FileTypes fileType;
  private String fileUploadsDir;


  public String getFileUploadsDir() {
    return fileUploadsDir;
  }

  public void setFileUploadsDir(String fileUploadsDir) {
    this.fileUploadsDir = fileUploadsDir;
  }

  public FilePath(String userId) {
    this.userId = userId;
    fileUploadsDir = "/home/sunny/FileUploads";
    subFolderPath = generateSubFolder();
    // New UUID for storing file
    uuid = UUID.randomUUID().toString();
  }

  public String full() {
    return this.fileUploadsDir + File.separator + subFolderPath + File.separator + fileName();
  }
  public String partial() {
    return subFolderPath + File.separator + fileName();
  }
  public String fileName() {
    return uuid+"."+fileType.small;
  }

  public FileTypes getFileType() {
    return fileType;
  }

  public void setFileType(FileTypes fileType) {
    this.fileType = fileType;
  }


  public String getSubFolderPath() {
    return subFolderPath;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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

  private String generateSubFolder() {
    return userId+File.separator+getFormattedDate();
  }
}
