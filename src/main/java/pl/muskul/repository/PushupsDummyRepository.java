package pl.muskul.repository;

import pl.muskul.entity.User;

import java.util.ArrayList;

public class PushupsDummyRepository {
    private ArrayList<User> users = new ArrayList<>() {
        {
            add(new User("bade@poczta.fm"));
        }
    };

    public void addUser(User user) {
        users.add(user);
    }

    public User[] getUsers() {
        return users.toArray(new User[users.size()]);
    }

    public User autorize(String token) {
        return users.stream().filter(user -> user.getToken().equals(token)).findFirst().get();
    }

}
