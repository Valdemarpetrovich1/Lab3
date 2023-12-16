package com.mahota.authorize.controller;
import com.mahota.authorize.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/View")
public class ViewController
{
    private final FileService fileService;

    @Autowired
    public ViewController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{fileName}")
    public String viewFile(@PathVariable String fileName, Model model)
    {
        String fileContent = fileService.getFileContentByName(fileName);
        model.addAttribute("fileName", fileName);
        model.addAttribute("fileContent", fileContent);
        return "View";
    }
}
