package com.klef.soa.docker.repository;

import com.klef.soa.docker.entity.VolunteerActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerActivityRepository extends JpaRepository<VolunteerActivity, Long> {
}
