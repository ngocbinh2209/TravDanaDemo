package com.danang.TravDana.app.repository;

import com.danang.TravDana.app.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SCheduleRepo extends JpaRepository<ScheduleEntity, Integer> {
}
