package com.example.uasrpl.Config;

public class Link {
    private final String event = "http://192.168.100.26/webinar/ryan/event.php";
    private final String peserta = "http://192.168.100.26/webinar/ryan/peserta.php";

    public String getEvent() {
        return event;
    }

    public String getPeserta() {
        return peserta;
    }
}
