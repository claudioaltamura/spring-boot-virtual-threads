package de.claudioaltamura.spring.boot.virtual.threads;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class ComputatiionController {

    private final ComputationService computationService;

    public ComputatiionController(ComputationService computationService) {
        this.computationService = computationService;
    }

    @GetMapping("/calculate/{number}")
    public CalculatedNumber calculate(@PathVariable("number") int number) {
        return computationService.calculate(number)
                        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }
}
