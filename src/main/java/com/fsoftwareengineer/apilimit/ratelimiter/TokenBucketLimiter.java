package com.fsoftwareengineer.apilimit.ratelimiter;

import com.fsoftwareengineer.apilimit.exception.ThrottleException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class TokenBucketLimiter {

  private static final Integer MAX_BUCKET_SIZE_PER_MIN = 10;
  private static Integer bucket = MAX_BUCKET_SIZE_PER_MIN;

  static {
    new Timer().scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
          increase();
      }
    }, 1 * 60 * 1000, 1 * 60 * 1000); // every minute, refil to 10
  }

  public synchronized static void increase() {
    bucket = MAX_BUCKET_SIZE_PER_MIN;
    log.info("Reset the bucket size to {}", bucket);
  }

  public synchronized static void decrease() throws ThrottleException {
    if(bucket > 0) {
      bucket--;
      log.info("decreased the bucket size to {}", bucket);
      return;
    }

    LocalTime time = LocalTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    throw new ThrottleException("Request Throttled " + time.format(formatter));
  }

}
