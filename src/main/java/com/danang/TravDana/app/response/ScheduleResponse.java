package com.danang.TravDana.app.response;

import com.danang.TravDana.app.entity.SiteEntity;
import com.danang.TravDana.app.entity.TourEntity;
import jakarta.persistence.*;

import java.util.Date;

public class ScheduleResponse {

    private TourEntity tour;

    private SiteEntity site;

    private Date startTime;

    private Date endTime;

    private int dayNumber;

}
