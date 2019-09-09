package pl.muskul.entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Pushups {
    private LocalDate date = LocalDate.now();
    private Short day = 1;
    private Map<Integer, Integer> series = new HashMap<>();

    public Pushups() {}
    public Pushups(Short day) {
        this.day = day;
    }

    public LocalDate getDate() {
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
