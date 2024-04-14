package com.HotelMalik.reserv2.dto;

import com.HotelMalik.reserv2.entities.Photo;
import com.HotelMalik.reserv2.entities.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RoomDto {

    private Long roomId;
    private float prix;
    private String description;
    private int capacity;
    private boolean isavailable;
    private Collection<String> photoUrls;

    public static RoomDto toDto(Room entity) {
        return RoomDto.builder()
                .roomId(entity.getRoomid())
                .prix(entity.getPrix())
                .description(entity.getDescription())
                .capacity(entity.getCapacity())
                .isavailable(entity.isIsavailable())
                .photoUrls(entity.getPhotoCollection()
                        .stream()
                        .map(Photo::getUrl)
                        .collect(Collectors.toList()))
                .build();
    }

    public static Room toEntity(RoomDto roomDto) {
        return Room.builder()
                .prix(roomDto.getPrix())
                .description(roomDto.getDescription())
                .capacity(roomDto.getCapacity())
                .isavailable(roomDto.isavailable)
                .photoCollection(roomDto.getPhotoUrls().stream()
                        .map(url -> {
                            Photo photo = new Photo();
                            photo.setUrl(url);
                            return photo;
                        })
                        .collect(Collectors.toList()))
                .build();
    }
}
