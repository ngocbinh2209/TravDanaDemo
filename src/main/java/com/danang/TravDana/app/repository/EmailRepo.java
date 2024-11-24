package com.danang.TravDana.app.repository;

import com.danang.TravDana.app.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepo extends JpaRepository<EmailEntity , Integer> {
}
