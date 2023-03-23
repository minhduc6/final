package com.example.springsocial.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileUtil {

    private FileUtil() {
        // restrict instantiation
    }

    public static final String folderPath = "incoming-files//";
    public static final Path filePath = Paths.get(folderPath);
    public static String uploadFile(MultipartFile file) {
        try {
            byte[] bytes = new byte[0];
            try {
                bytes = file.getBytes();
                Files.write(Paths.get(FileUtil.folderPath + file.getOriginalFilename()), bytes);
            } catch (IOException e) {

            }
            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/incoming-files/")
                    .path(file.getOriginalFilename())
                    .toUriString();

            return downloadUrl;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}

