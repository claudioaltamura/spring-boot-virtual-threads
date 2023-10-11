package de.claudioaltamura.spring.boot.virtual.threads;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Service;

@Service
public class ComputationService {

  public Optional<CalculatedNumber> calculate(int number) {
    try {
      var minAsMillis = 1000;
      var maxAsMillis = minAsMillis * 5;
      Thread.sleep(ThreadLocalRandom.current().nextLong(minAsMillis, maxAsMillis));

      var randomNumber = ThreadLocalRandom.current().nextInt(1 + number) + 1;

      return Optional.of(new CalculatedNumber(randomNumber));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }
}
