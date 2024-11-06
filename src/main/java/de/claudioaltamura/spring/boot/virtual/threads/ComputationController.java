package de.claudioaltamura.spring.boot.virtual.threads;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ComputationController {

  private final ComputationService computationService;

  public ComputationController(ComputationService computationService) {
    this.computationService = computationService;
  }

  @GetMapping("/calculate/{number}")
  public CalculatedNumber calculate(@PathVariable("number") int number) {
    return computationService
        .calculate(number)
        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
  }
}
