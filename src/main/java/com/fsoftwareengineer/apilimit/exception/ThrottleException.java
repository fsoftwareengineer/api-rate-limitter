package com.fsoftwareengineer.apilimit.exception;

public class ThrottleException extends Exception{
  public ThrottleException(String message) {
    super(message);
  }
}
