package br.com.valdemarjr.web.controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class ProductController {

  private static final Logger log = LoggerFactory.getLogger(ProductController.class);

  private final String BASE_URL = "http://localhost:8080";
  private final RestClient restClient;

  public ProductController(RestClient.Builder builder) {
    this.restClient = builder.baseUrl(BASE_URL).build();
  }

  @GetMapping("/async")
  void getAsync() throws ExecutionException, InterruptedException {
    log.info("Starting async calls...");
    var starTime = System.currentTimeMillis();

    var response = CompletableFuture.supplyAsync(() -> getResponseIn(3));
    var response2 = CompletableFuture.supplyAsync(() -> getResponseIn(2));
    CompletableFuture.allOf(response, response2).join();

    response.get();
    response2.get();

    var endTime = System.currentTimeMillis();
    log.info("Total time: {} seconds", (endTime - starTime) / 1000);
  }

  @GetMapping("/sync")
  void get() {
    log.info("Starting sync calls...");
    var starTime = System.currentTimeMillis();
    getResponseIn(3);
    getResponseIn(2);

    var endTime = System.currentTimeMillis();
    log.info("Total time: {} seconds", (endTime - starTime) / 1000);
  }

  private String getResponseIn(Integer seconds) {
    var body = this.restClient.get().uri("/delay?inSeconds=" + seconds).retrieve().body(String.class);
    log.info("Response: {}", body);

    return body;
  }
}
