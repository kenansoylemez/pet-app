package com.example.evcil_hayvan.dto.delete;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeletePetPhotoDto {

    @NotNull(message = "Silinecek fotoğrafın Id'si boş olamaz.")
    private Long petPhotoId;

    @NotNull(message = "İşlemi yapan kullanıcının ID'si boş olamaz.")
    private Long ownerId;

}

