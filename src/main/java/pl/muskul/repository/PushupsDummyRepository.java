package pl.muskul.repository;

import pl.muskul.entity.Pushups;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class PushupsDummyRepository {
    private Map<String, List<Pushups>> pushups = new HashMap<>(Map.of("1", new ArrayList<>() {
    }));

    public Pushups[] getPushups(String userId) {
        List userPushups = pushups.get(userId);

        if (userPushups != null) {
            return pushups.get(userId).toArray(Pushups[]::new);
        }

        return new Pushups[]{};
    }

    public void addPushup(String userId, Pushups newPushups) {
        if (pushups.containsKey(userId)) {
            pushups.get(userId).add(newPushups);
        } else {
            pushups.put(userId, new ArrayList<Pushups>());
            pushups.get(userId).add(newPushups);
        }
    }

    public void removePushups(String userId, LocalDateTime removeDate) {
        List<Pushups> somePushups = pushups.get(userId).stream().filter(pushup -> !pushup.getDate().isEqual(removeDate)).collect(Collectors.toList());
        pushups.put(userId, somePushups);
    }
}
