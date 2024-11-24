package com.danang.TravDana.app.service;

import com.danang.TravDana.app.entity.ScheduleEntity;

import java.util.List;

public interface ScheduleService {
    List<ScheduleEntity> findAll();

    ScheduleEntity findById(int id);
}
