package com.klef.soa.docker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class VolunteerActivityRequest {
    @NotBlank(message = "Activity name is required")
    @Size(max = 120, message = "Activity name must not exceed 120 characters")
    private String activityName;

    @NotBlank(message = "Organizer name is required")
    @Size(max = 120, message = "Organizer name must not exceed 120 characters")
    private String organizerName;

    @NotNull(message = "Volunteer count is required")
    @PositiveOrZero(message = "Volunteer count cannot be negative")
    private Integer volunteerCount;

    @NotNull(message = "Active status is required")
    private Boolean active;

    public String getActivityName() { return activityName; }
    public void setActivityName(String activityName) { this.activityName = activityName; }
    public String getOrganizerName() { return organizerName; }
    public void setOrganizerName(String organizerName) { this.organizerName = organizerName; }
    public Integer getVolunteerCount() { return volunteerCount; }
    public void setVolunteerCount(Integer volunteerCount) { this.volunteerCount = volunteerCount; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
