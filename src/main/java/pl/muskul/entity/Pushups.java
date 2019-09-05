package pl.muskul.entity;

import java.time.LocalDate;

public class Pushups {
    private LocalDate date = LocalDate.now();
    private Short day = 1;
    private Series[] series = {};

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
    
    public Series[] getSeries() {
        return series;
    }
}

class Series {
    private Short no;
    private Short score;

    public Series(Short no, Short score) {
        this.no = no;
        this.score = score;
    }
}
