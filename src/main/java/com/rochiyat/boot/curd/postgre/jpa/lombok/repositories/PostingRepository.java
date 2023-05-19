package com.rochiyat.boot.curd.postgre.jpa.lombok.repositories;


import com.rochiyat.boot.curd.postgre.jpa.lombok.models.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {

    List<Posting> findByPublished(boolean published);
    List<Posting> findByTittleContaining(String tittle);
}
