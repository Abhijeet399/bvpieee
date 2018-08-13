package com.jskgmail.bvpieee;

public class Event_info {
    String topic;
    String info;
    int[] photo;

    public Event_info(String topic, String info, int[] photo) {
        this.topic = topic;
        this.info = info;
        this.photo = photo;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int[] getPhoto() {
        return photo;
    }

    public void setPhoto(int[] photo) {
        this.photo = photo;
    }
}
