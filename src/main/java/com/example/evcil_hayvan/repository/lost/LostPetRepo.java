package com.example.evcil_hayvan.repository.lost;

import com.example.evcil_hayvan.entity.lost.MissingNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostPetRepo extends JpaRepository<MissingNotice,Long> {
}
