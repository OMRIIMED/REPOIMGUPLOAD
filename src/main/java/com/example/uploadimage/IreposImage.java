package com.example.uploadimage;

import com.example.uploadimage.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IreposImage extends JpaRepository<Image, Long> {

}
