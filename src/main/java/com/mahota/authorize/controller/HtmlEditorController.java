package com.mahota.authorize.controller;

import com.mahota.authorize.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/editor")
public class HtmlEditorController
{
    private final FileService fileService;
    @GetMapping
    public String editor() {
        return "editor";
    }

    @Autowired
    public HtmlEditorController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{fileName}")
    public String getEditPage(@PathVariable String fileName, Model model) {
        //System.out.println("work");
        String content = fileService.readFileContent(fileName);
        model.addAttribute("fileName", fileName);
        model.addAttribute("fileContent", content);
        return "editor";
    }

    @GetMapping ("/delete/{fileName}")
    public String deleteFile(@PathVariable String fileName) {
        fileService.deleteFileByName(fileName);
        return "redirect:/explorer";
    }

    @PostMapping
    public String saveChanges(@RequestParam("fileName") String fileName,
                              @RequestParam("fileContent") String fileContent,
                              Model model) {
        //System.out.println("work");
        fileService.saveFileContent(fileName, fileContent);
        return "redirect:/editor/" + fileName;
    }

    @PostMapping("/{fileName}")
    public String saveFileContent(
            @PathVariable String fileName,
            @RequestParam String content
    ) {
        fileService.saveFileContent(fileName, content);
        return "redirect:/edit/{fileName}";
    }
}
