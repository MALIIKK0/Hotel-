package com.HotelMalik.reserv2.controller;

import com.HotelMalik.reserv2.entities.Photo;
import com.HotelMalik.reserv2.entities.Room;
import com.HotelMalik.reserv2.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PhotoController {

    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/photos")
    public ResponseEntity<Photo> addPhoto(@RequestBody Photo photo) {
        Photo savedPhoto = photoService.addPhoto(photo);
        return new ResponseEntity<>(savedPhoto, HttpStatus.CREATED);
    }

    @GetMapping("/photos")
    public List<Photo> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable Long id) {
        Photo photo = photoService.getPhotoById(id);
        return
                ResponseEntity.ok(photo) ;

    }

    // Update photo
    @PutMapping("/photos/{id}")
    public ResponseEntity<Photo> updateRoom(@PathVariable Long id, @RequestBody Photo photoDetails) {
        Photo updatedPhoto = photoService.updatePhoto(id,photoDetails);
        return ResponseEntity.ok(updatedPhoto);
    }

    @DeleteMapping("/photos/{id}")
    public ResponseEntity<String> deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);
        return ResponseEntity.ok("Photo with ID " + id + " has been deleted");
    }
}
