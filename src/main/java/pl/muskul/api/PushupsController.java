package pl.muskul.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.muskul.Application;
import pl.muskul.entity.Pushups;
import pl.muskul.entity.User;
import pl.muskul.repository.PushupsDummyRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@RestController
public class PushupsController {
    PushupsDummyRepository repo = new PushupsDummyRepository();

    @GetMapping("/pushups")
    public ResponseEntity<?> getPushups(HttpServletRequest request) throws JsonProcessingException {
        Pushups[] pushups = repo.getPushups((String) request.getSession().getAttribute("userId"));

        return new ResponseEntity<>(pushups, HttpStatus.OK);
    }

    @PostMapping("/pushups")
    public ResponseEntity<?> addPushups(HttpServletRequest request) throws JsonProcessingException {
        String userId = (String) request.getSession().getAttribute("userId");
        Pushups pushups = new Pushups();
        pushups.setSerie(1, 12);
        pushups.setSerie(2, 15);
        pushups.setSerie(3, 11);
        pushups.setSerie(4, 11);
        pushups.setSerie(5, 18);
        repo.addPushup(userId, pushups);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
