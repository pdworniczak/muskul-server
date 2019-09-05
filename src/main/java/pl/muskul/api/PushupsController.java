package pl.muskul.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.muskul.Application;
import pl.muskul.entity.Pushups;
import pl.muskul.entity.User;
import pl.muskul.repository.PushupsDummyRepository;

import java.util.NoSuchElementException;

@RestController
public class PushupsController {
    PushupsDummyRepository repo = new PushupsDummyRepository();

    @GetMapping("/pushups")
    public ResponseEntity<?> pushups() throws JsonProcessingException {
        try {
            User user = repo.autorize("test123");
            return new ResponseEntity<>(user.getTrainings(), HttpStatus.OK);
        } catch (NoSuchElementException err) {
            return new ResponseEntity<>("Unautorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/pushups")
    public void pushups(@RequestParam(value = "series") String series) {

    }
}
