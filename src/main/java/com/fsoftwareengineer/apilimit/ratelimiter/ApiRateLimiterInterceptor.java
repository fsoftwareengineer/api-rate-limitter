package com.fsoftwareengineer.apilimit.ratelimiter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsoftwareengineer.apilimit.exception.ThrottleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ApiRateLimiterInterceptor implements HandlerInterceptor {


  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    try {
      TokenBucketLimiter.decrease();
      log.info("Forwarding the request..");
      return true;
    } catch (ThrottleException e) {
      ObjectMapper mapper = new ObjectMapper();
      response.setContentType("application/json");
      response.setStatus(429); // Too many requests
      response.getWriter().write(mapper.writeValueAsString("Request Throttled."));
      log.info("Request Throttled.");
      return false;
    }
  }
}
