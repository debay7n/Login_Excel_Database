package com.loginexcel.loginexcel.controller;
import com.loginexcel.loginexcel.helper.excelHelper;
import com.loginexcel.loginexcel.model.userDetails;
import com.loginexcel.loginexcel.service.excelService;
import com.loginexcel.loginexcel.service.userService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;
import java.util.Map;

@Controller
public class userController {
    @Autowired
    private excelService excelService;

    @Autowired
    private userService userService;

    @PostMapping("/upload")
    public String save(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (excelHelper.checkExcelFormat(file)) {
            this.excelService.save(file);
            redirectAttributes.addFlashAttribute("message", "File is uploaded and saved to DB");
            return "redirect:/start";
        }

        redirectAttributes.addFlashAttribute("error", "Please upload an Excel file");
        return "redirect:/upload-page";
    }


//    @GetMapping("/user")
//    public List<userDetails> getAllUser(){
//        return this.excelService.getAllUser();
//    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @GetMapping("/start")
    public String getStarted() {
        return "start";
    }

    @GetMapping("/login")
    public String getLogged() {
        return "login";
    }

    @PostMapping("/sign")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if (userService.checkCredentials(username, password)) {
            return ResponseEntity.ok("Login successful");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }



}
}