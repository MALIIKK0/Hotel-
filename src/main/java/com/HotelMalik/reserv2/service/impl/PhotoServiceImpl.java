package com.HotelMalik.reserv2.service.impl;

import com.HotelMalik.reserv2.Exception.ResourceNotFoundException;
import com.HotelMalik.reserv2.entities.Photo;
import com.HotelMalik.reserv2.entities.User;
import com.HotelMalik.reserv2.repositories.PhotoRepository;
import com.HotelMalik.reserv2.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;



    @Override
    public Photo addPhoto(Photo photo) {
        return photoRepository.save(photo) ;
    }

    @Override
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    @Override
    public Photo getPhotoById(Long id) {
        return photoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("photo not found with id =  " + id));
    }

    @Override
    public Photo updatePhoto(Long id, Photo UpdatedPhoto) {
        Photo photo = getPhotoById(id);
        photo.setUrl(UpdatedPhoto.getUrl());
        photo.setDescription(UpdatedPhoto.getDescription());
        photo.setRoom(UpdatedPhoto.getRoom());
        return photoRepository.save(photo);
    }

    @Override
    public void deletePhoto(Long id) {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("photo not found with the given id"+id));
        photoRepository.deleteById(id);
    }
}
