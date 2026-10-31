package com.klef.soa.docker.service;

import com.klef.soa.docker.dto.VolunteerActivityRequest;
import com.klef.soa.docker.dto.VolunteerActivityResponse;
import java.util.List;

public interface VolunteerActivityService {
    VolunteerActivityResponse addActivity(VolunteerActivityRequest request);
    List<VolunteerActivityResponse> getAllActivities();
    void deleteActivity(Long id);
}
