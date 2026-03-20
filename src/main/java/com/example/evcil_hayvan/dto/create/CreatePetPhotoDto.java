package com.example.evcil_hayvan.dto.create;

import com.example.evcil_hayvan.dto.OwnerPetBaseDto;
import lombok.Data;

@Data
public class CreatePetPhotoDto extends OwnerPetBaseDto {

    private String caption;

    private String photoUrl;
}
