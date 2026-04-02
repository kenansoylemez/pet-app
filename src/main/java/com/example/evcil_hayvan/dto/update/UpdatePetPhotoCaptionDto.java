package com.example.evcil_hayvan.dto.update;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdatePetPhotoCaptionDto {

    @NotNull(message = "Güncellenecek fotoğrafın ID'si boş olamaz.")
    private Long petPhotoId;

    @NotNull(message = "İşlemi yapan kullanıcının ID'si boş olamaz.")
    private Long ownerId;
    private String newCaption;
}