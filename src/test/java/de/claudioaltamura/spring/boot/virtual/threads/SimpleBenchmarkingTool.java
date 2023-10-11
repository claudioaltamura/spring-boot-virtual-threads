package de.claudioaltamura.spring.boot.virtual.threads;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

class SimpleBenchmarkingTool {

  private static final String URL = "http://localhost:8080/calculate";

  private static final int NUMBER_OF_CLIENTS = 10;

  private static final int NUMBER_OF_REQUESTS = 10;

  private static final int DURATION_SECONDS = 10;

  public static void main(String[] args) {
    System.out.println("clients: " + NUMBER_OF_CLIENTS);
    System.out.println("number of requests: " + NUMBER_OF_CLIENTS);
    System.out.println("duration: " + DURATION_SECONDS);
    System.out.println("results: ");

    try (var executorService = Executors.newFixedThreadPool(NUMBER_OF_CLIENTS);
        var client =
            HttpClient.newBuilder()
                .executor(executorService)
                .version(Version.HTTP_2)
                .followRedirects(Redirect.NORMAL)
                .build()) {

      var requests = new ArrayList<HttpRequest>();
      for (int i = 1; i <= NUMBER_OF_REQUESTS; i++) {
        requests.add(
            HttpRequest.newBuilder()
                .uri(new URI(URL + "/10"))
                .GET()
                .timeout(Duration.ofSeconds(DURATION_SECONDS))
                .build());
      }

      long start = System.currentTimeMillis();

      var sendRequests =
          requests.stream()
              .map(
                  req ->
                      client
                          .sendAsync(req, BodyHandlers.ofString())
                          .thenApply(HttpResponse::body)
                          .thenAccept(System.out::println))
              .toList();

      CompletableFuture.allOf(sendRequests.toArray(new CompletableFuture[0])).join();

      long finish = System.currentTimeMillis();
      long timeElapsed = finish - start;

      System.out.println("Time elapsed: " + timeElapsed + " ms");
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
