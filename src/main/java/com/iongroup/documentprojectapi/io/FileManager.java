package com.iongroup.documentprojectapi.io;

import com.iongroup.documentprojectapi.entity.Document;
import com.iongroup.documentprojectapi.exception.FileSaveException;
import com.iongroup.documentprojectapi.exception.NotFoundException;
import com.iongroup.documentprojectapi.util.ExceptionMessage;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    private static final String directory = "C:/Users/Daniel/Desktop/document-project-files/";

    public static String saveFile(Document document, MultipartFile multipartFile) {
        String institutionName = document.getInstitution().getName();
        int year = document.getUploadDate().getYear();
        int month = document.getUploadDate().getMonthValue();

        String filePath = institutionName  + "/" + year + "/" + month;
        String fileName = "/" + multipartFile.getOriginalFilename();

        try {
            Path fullPath = Paths.get(directory, filePath, fileName);
            while (Files.exists(fullPath)) {
                fileName = addIndexBeforeExtension(fileName);
                fullPath = Paths.get(directory, filePath, fileName);
            }
            Files.createDirectories(fullPath.getParent());
            multipartFile.transferTo(fullPath.toFile());
        } catch (IOException e) {
            throw new FileSaveException(ExceptionMessage.SAVE_FILE_FAIL);
        }

        return filePath + fileName;
    }

    public static Resource getFile(Document document) {
        Path path = Paths.get(directory + document.getPath());

        try {
            return new UrlResource(path.getParent().resolve(path.getFileName()).toUri());
        } catch (Exception e) {
            throw NotFoundException.of("File");
        }
    }

    private static String addIndexBeforeExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, lastDotIndex) + "(1)" + fileName.substring(lastDotIndex);
    }
}
