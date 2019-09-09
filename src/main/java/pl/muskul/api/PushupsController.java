package pl.muskul.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.muskul.entity.Pushups;
import pl.muskul.repository.PushupsDummyRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@RestController
public class PushupsController {
    PushupsDummyRepository repo = new PushupsDummyRepository();

    @GetMapping("/pushups")
    public ResponseEntity<?> getPushups(HttpServletRequest request) throws JsonProcessingException {
        Pushups[] pushupsTrainings = repo.getPushups((String) request.getSession().getAttribute("userId"));

        return new ResponseEntity<>(pushupsTrainings, HttpStatus.OK);
    }

    @PostMapping("/pushups")
    public ResponseEntity<?> addPushups(@RequestBody Pushups pushups, HttpServletRequest request) throws JsonProcessingException {
        String userId = (String) request.getSession().getAttribute("userId");
        repo.addPushup(userId, pushups);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/pushups")
    public ResponseEntity<?> deletePushups(@RequestBody LinkedHashMap<String, String> body, HttpServletRequest request) throws JsonProcessingException {
        String userId = (String) request.getSession().getAttribute("userId");
        LocalDateTime dateTime = LocalDateTime.parse(body.get("date"));
        repo.removePushups(userId, dateTime);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
