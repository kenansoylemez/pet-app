package com.example.evcil_hayvan.repository.lost;

import com.example.evcil_hayvan.entity.Pet;
import com.example.evcil_hayvan.entity.lost.MissingNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissingNoticeRepo extends JpaRepository<MissingNotice,Long> {

    List<MissingNotice> findMissingNoticeByLastSeenLocation(String lastSeenLocation);
    List<MissingNotice> findMissingNoticeByPetId(Long petId);
    List<MissingNotice> findMissingNoticeByOwner_OwnerId(Long ownerId);
    Optional<MissingNotice> findMissingNoticeById(Long id);

}
