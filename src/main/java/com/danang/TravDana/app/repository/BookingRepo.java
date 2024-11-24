package com.danang.TravDana.app.repository;
import com.danang.TravDana.app.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<BookingEntity, Integer>{
}
