package com.orejita.games.Services.UploadFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.orejita.games.Exceptions.UploadFile.FileStorageException;
import com.orejita.games.Exceptions.UploadFile.MyFileNotFoundException;

@Service
public class UploadFileService {

    @Value("${file.upload-dir}")
    private String uploadDir = "";

    public String storeFile(MultipartFile file, String filename) {
        String fileName = "";
        if (filename != null) {
            fileName = filename + "-" + StringUtils.cleanPath(file.getOriginalFilename());
        } else {
            fileName = StringUtils.cleanPath(file.getOriginalFilename());
        }

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("FileName containss invalid path section(s)");
            }

            Path targetLocation = Paths.get(this.uploadDir + fileName);
            if (new File(targetLocation.toUri()).exists()) {
                Files.write(targetLocation, file.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            } else {
                Files.write(targetLocation, file.getBytes(), StandardOpenOption.CREATE_NEW);
            }

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not sotre file " + fileName, ex);
        }
    }

    public Resource loadFile(String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).toAbsolutePath().resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("Image not found");
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("Image not found", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).toAbsolutePath().resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

}
