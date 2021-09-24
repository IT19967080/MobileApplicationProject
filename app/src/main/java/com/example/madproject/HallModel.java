package com.example.madproject;

public class HallModel {
    private int id;
    private String hallId,capacity,ac,hallManager,teacher;
    private long started, finished;

    public HallModel(){

    }

    public HallModel(int id, String hallId, String capacity, String ac, String hallManager, String teacher, long started, long finished) {
        this.id = id;
        this.hallId = hallId;
        this.capacity = capacity;
        this.ac = ac;
        this.hallManager = hallManager;
        this.teacher = teacher;
        this.started = started;
        this.finished = finished;
    }

    public HallModel(String hallId, String capacity, String ac, String hallManager, String teacher, long started, long finished) {
        this.hallId = hallId;
        this.capacity = capacity;
        this.ac = ac;
        this.hallManager = hallManager;
        this.teacher = teacher;
        this.started = started;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getHallManager() {
        return hallManager;
    }

    public void setHallManager(String hallManager) {
        this.hallManager = hallManager;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
}




















