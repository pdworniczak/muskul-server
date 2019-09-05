package pl.muskul.entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {
    private String email;
    private String token;
    private ArrayList<Pushups> trainings = new ArrayList<>();

    public User(String email) {
        this.email = email;
        this.token = "test123";
    }

    public String getToken() {
        return token;
    }

    public void addTraining(Pushups training) {
        this.trainings.add(training);
    }

    public Pushups removeTraining(LocalDate date) {
        Pushups toRemove = null;

        for (Pushups training: this.trainings) {
            if (training.getDate().isEqual(date)) {
                toRemove = training;
                break;
            }
        }

        this.trainings.remove(toRemove);

        return toRemove;
    }

    public Pushups[] getTrainings() {
        return this.trainings.toArray(new Pushups[this.trainings.size()]);
    }
}
