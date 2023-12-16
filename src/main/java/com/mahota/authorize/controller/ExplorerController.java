package com.mahota.authorize.controller;

import com.mahota.authorize.service.FileService;
import com.mahota.authorize.entity.FileSystemItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/explorer")
public class ExplorerController {
    private final FileService fileService;

    @Autowired
    public ExplorerController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public String getFileExplorer(Model model) {
        List<FileSystemItem> files = fileService.getAllFiles();
        model.addAttribute("fileSystemItems", files);
        return "explorer";
    }

    @PostMapping("/createfile")
    public String addFile(@RequestParam("fileName") String fileName) {
        String nfileName = fileName+".html";
        fileService.createFile(nfileName);
        return "redirect:/editor/" + nfileName;
    }
}
