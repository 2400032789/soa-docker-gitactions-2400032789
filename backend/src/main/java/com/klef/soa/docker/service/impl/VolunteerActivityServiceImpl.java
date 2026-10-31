package com.klef.soa.docker.service.impl;

import com.klef.soa.docker.dto.VolunteerActivityRequest;
import com.klef.soa.docker.dto.VolunteerActivityResponse;
import com.klef.soa.docker.entity.VolunteerActivity;
import com.klef.soa.docker.repository.VolunteerActivityRepository;
import com.klef.soa.docker.service.VolunteerActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class VolunteerActivityServiceImpl implements VolunteerActivityService {
    private final VolunteerActivityRepository repository;

    public VolunteerActivityServiceImpl(VolunteerActivityRepository repository) {
        this.repository = repository;
    }

    @Override
    public VolunteerActivityResponse addActivity(VolunteerActivityRequest request) {
        VolunteerActivity activity = new VolunteerActivity(
                request.getActivityName().trim(),
                request.getOrganizerName().trim(),
                request.getVolunteerCount(),
                request.getActive());
        return toResponse(repository.save(activity));
    }

    @Override
    public List<VolunteerActivityResponse> getAllActivities() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public void deleteActivity(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity with id " + id + " not found");
        }
        repository.deleteById(id);
    }

    private VolunteerActivityResponse toResponse(VolunteerActivity a) {
        return new VolunteerActivityResponse(a.getId(), a.getActivityName(), a.getOrganizerName(),
                a.getVolunteerCount(), a.getActive());
    }
}
