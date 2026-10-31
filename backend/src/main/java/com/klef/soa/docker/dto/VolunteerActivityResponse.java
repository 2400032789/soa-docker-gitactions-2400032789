package com.klef.soa.docker.dto;

public class VolunteerActivityResponse {
    private Long id;
    private String activityName;
    private String organizerName;
    private Integer volunteerCount;
    private Boolean active;

    public VolunteerActivityResponse(Long id, String activityName, String organizerName, Integer volunteerCount, Boolean active) {
        this.id = id;
        this.activityName = activityName;
        this.organizerName = organizerName;
        this.volunteerCount = volunteerCount;
        this.active = active;
    }

    public Long getId() { return id; }
    public String getActivityName() { return activityName; }
    public String getOrganizerName() { return organizerName; }
    public Integer getVolunteerCount() { return volunteerCount; }
    public Boolean getActive() { return active; }
}
