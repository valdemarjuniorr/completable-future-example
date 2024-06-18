package br.com.valdemarjr.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delay")
public class DelayController {

  private static final Logger log = LoggerFactory.getLogger(DelayController.class);

  /**
   * Delay the response for a given number of seconds.
   */
  @GetMapping
  String delay(@RequestParam("inSeconds") Integer delayInSec) {
    try {
      Thread.sleep(delayInSec * 1000);
    } catch (InterruptedException e) {
      log.info("delay interrupted");
    }
    return "Delay of " + delayInSec + " seconds";
  }
}
