package pl.muskul.repository;

import pl.muskul.entity.Pushups;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class PushupsDummyRepository {
    private Map<String, List<Pushups>> pushups = new HashMap<>(Map.of("1", new ArrayList<>() {
    }));

    public Pushups[] getPushups(String userId) {
        return pushups.get(userId).toArray(Pushups[]::new);
    }

    public void addPushup(String userId, Pushups newPushups) {
        pushups.get(userId).add(newPushups);
    }

    public void removePushups(String userId, LocalDateTime removeDate) {
        List<Pushups> somePushups = pushups.get(userId).stream().filter(pushup -> !pushup.getDate().isEqual(removeDate)).collect(Collectors.toList());
        pushups.put(userId, somePushups);
    }
}
