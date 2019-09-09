package pl.muskul.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Pushups {
    private LocalDateTime date = LocalDateTime.now();
    private Short day = 1;
    private Map<Integer, Integer> series = new HashMap<>();

    public Pushups() {
    }

    public Pushups(Short day) {
        this.day = day;
    }

    public Pushups(Short day, Map<Integer, Integer> series) {
        this.day = day;
        this.series = series;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Short getDay() {
        return day;
    }

    public Map<Integer, Integer> getSeries() {
        return series;
    }

    public void setSerie(Integer no, Integer value) {
        series.put(no, value);
    }
}
