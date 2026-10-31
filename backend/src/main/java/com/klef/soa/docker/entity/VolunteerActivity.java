package com.klef.soa.docker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "volunteer_activity")
public class VolunteerActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activity_name", nullable = false, length = 120)
    private String activityName;

    @Column(name = "organizer_name", nullable = false, length = 120)
    private String organizerName;

    @Column(name = "volunteer_count", nullable = false)
    private Integer volunteerCount;

    @Column(nullable = false)
    private Boolean active;

    public VolunteerActivity() {}

    public VolunteerActivity(String activityName, String organizerName, Integer volunteerCount, Boolean active) {
        this.activityName = activityName;
        this.organizerName = organizerName;
        this.volunteerCount = volunteerCount;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getActivityName() { return activityName; }
    public void setActivityName(String activityName) { this.activityName = activityName; }
    public String getOrganizerName() { return organizerName; }
    public void setOrganizerName(String organizerName) { this.organizerName = organizerName; }
    public Integer getVolunteerCount() { return volunteerCount; }
    public void setVolunteerCount(Integer volunteerCount) { this.volunteerCount = volunteerCount; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
