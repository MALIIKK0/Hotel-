package com.HotelMalik.reserv2.service;

import com.HotelMalik.reserv2.entities.Photo;
import com.HotelMalik.reserv2.entities.Room;

import java.util.List;

public interface PhotoService {
    Photo addPhoto(Photo photo);
    List<Photo> getAllPhotos();
    Photo getPhotoById(Long id);
    Photo updatePhoto(Long id, Photo UpdatedPhoto);
    void deletePhoto(Long id);
}
