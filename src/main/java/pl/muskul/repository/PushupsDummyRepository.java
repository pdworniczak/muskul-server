package pl.muskul.repository;

import pl.muskul.entity.Pushups;
import pl.muskul.entity.User;

import java.util.*;
import java.util.stream.Collectors;

public class PushupsDummyRepository {
    private Map<String, List<Pushups>> pushups = Map.of("1", new ArrayList<>(){});

    public Pushups[] getPushups(String userId) {
        return pushups.get(userId).toArray(Pushups[]::new);
    }

    public void addPushup(String userId, Pushups newPushups) {
        pushups.get(userId).add(newPushups);
    }

    public void removePushups(String userId, Date removeDate) {
        pushups.get(userId).stream().filter(pushup -> !pushup.getDate().equals(removeDate)).collect(Collectors.toList());
    }
}
