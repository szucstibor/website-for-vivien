package org.personal.drawingsite.drawRequest;

import javax.persistence.*;

@Entity
@Table(name = "requests")
public class DrawRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    @Column(name="name", nullable = false)
    private String requestName;

    @Column(name="description", nullable = false)
    private String requestDescription;

    @Column(name="complexity")
    private int complexity;

    @Column(name="estimate")
    private int estimatedTime;

    @Column(name = "accepted", nullable = false)
    private boolean accepted;

    @Column(name = "active", nullable = false)
    private boolean active = false;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "done")
    private boolean done = false;

    public DrawRequest(String requestName, String requestDescription) {
        this.requestName = requestName;
        this.requestDescription = requestDescription;
    }

    public DrawRequest() {
    }

    public DrawRequest(String requestName, String requestDescription, String username) {
        this.requestName = requestName;
        this.requestDescription = requestDescription;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean workingOn) {
        this.active = workingOn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
