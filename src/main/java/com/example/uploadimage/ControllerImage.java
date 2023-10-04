package com.example.uploadimage;


import com.example.uploadimage.entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;

    @RestController
    @RequestMapping("/images")
    public class ControllerImage {

        @Autowired
        private IreposImage img;

        @PostMapping("/upload")
        public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Please select a file to upload.");
            }
            try {
                Image image = new Image();
                image.setData(file.getBytes());
                img.save(image);
                return ResponseEntity.ok("Image uploaded successfully!");
            } catch (IOException e) {
                return ResponseEntity.status(500).body("Failed to upload image");
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
            return img.findById(id)
                    .map(image -> ResponseEntity.ok(image.getData()))
                    .orElse(ResponseEntity.notFound().build());
        }
    }

