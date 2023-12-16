package com.mahota.authorize.service;
import com.mahota.authorize.entity.FileSystemItem;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileService {

    private static final String ROOT_PATH = "src/main/resources/Temp";

    public List<FileSystemItem> getAllFiles() {
        File directory = new File(ROOT_PATH);

        if (!directory.exists() || !directory.isDirectory()) {
            throw new RuntimeException("Invalid directory path");
        }

        File[] files = directory.listFiles();

        if (files != null) {
            return Arrays.stream(files)
                    .filter(File::isFile)
                    .map(file -> new FileSystemItem(file.getName(), file.getAbsolutePath()))
                    .collect(Collectors.toList());
        }

        return List.of();
    }

    public void deleteFileByName(String fileName) {
        Path filePath = Paths.get(ROOT_PATH, fileName);

        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file", e);
        }
    }

    public void createFile(String name) {
        File newFile = new File(ROOT_PATH + File.separator + name);
        try {
            if (newFile.createNewFile()) {

            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readFileContent(String fileName)
    {
        Path filePath = Path.of(ROOT_PATH, fileName);
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file content", e);
        }
    }

    public void saveFileContent(String fileName, String content)
    {
        Path filePath = Path.of(ROOT_PATH, fileName);
        try {
            Files.write(filePath, content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file content", e);
        }
    }

    public String getFileContentByName(String fileName)
    {
        Path filePath = Paths.get(ROOT_PATH, fileName);

        try {
            // Чтение содержимого файла в строку
            return new String(Files.readAllBytes(filePath));
        } catch (IOException e) {
            // Обработка ошибки, например, логирование
            e.printStackTrace();
            return "Ошибка чтения файла";
        }
    }
}

