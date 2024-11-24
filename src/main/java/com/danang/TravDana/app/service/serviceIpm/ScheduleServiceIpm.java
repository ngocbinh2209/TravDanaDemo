package com.danang.TravDana.app.service.serviceIpm;

import com.danang.TravDana.app.entity.ScheduleEntity;
import com.danang.TravDana.app.repository.SCheduleRepo;
import com.danang.TravDana.app.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceIpm implements ScheduleService {

    @Autowired
    SCheduleRepo sCheduleRepo ;

    @Override
    public List<ScheduleEntity> findAll() {
        return sCheduleRepo.findAll();
    }
    @Override
    public ScheduleEntity findById(int id) {
        return sCheduleRepo.findById(id).get();
    }




}
