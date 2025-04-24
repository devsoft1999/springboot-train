package com.pakjai.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pakjai.dto.BaseModelRequest;


@RestController
@RequestMapping("/newapi")
public class NewApiController {

    // @PostMapping("/test")
    // public String test(@RequestBody BaseModelRequest request) {
    //     return "Hello World";
    // }

    // @GetMapping("/test")
    // public String test(@RequestParam Long id, @RequestParam String name) {
    //     return "Hello World : " + id + " " + name;
    // }


    // @PostMapping(value = "/testFormData", consumes = "multipart/form-data")
    // public ResponseEntity<String> testFormData(
    // @RequestBody BaseModelRequest request
    // ,@RequestParam("file") MultipartFile file) {
    //     if (file.isEmpty()) {
    //         return ResponseEntity.badRequest().body("File is empty");
    //     }
    //     try {
    //         String fileName = file.getOriginalFilename();
    //         long size = file.getSize();
    //         // คุณสามารถบันทึกไฟล์ลง disk หรือประมวลผลเพิ่มเติมที่นี่
    //         return ResponseEntity.ok("Uploaded file: " + fileName + ", Size: " + size + " bytes");
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    //     }
    // }

    // @PostMapping(value = "/testForm", consumes = "multipart/form-data")
    // public ResponseEntity<String> testForm(@RequestParam("file") MultipartFile file) {
    //     if (file.isEmpty()) {
    //         return ResponseEntity.badRequest().body("File is empty");
    //     }
    //     try {
    //         String fileName = file.getOriginalFilename();
    //         long size = file.getSize();
    //         // คุณสามารถบันทึกไฟล์ลง disk หรือประมวลผลเพิ่มเติมที่นี่
    //         return ResponseEntity.ok("Uploaded file: " + fileName + ", Size: " + size + " bytes");
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    //     }
    // }

}
