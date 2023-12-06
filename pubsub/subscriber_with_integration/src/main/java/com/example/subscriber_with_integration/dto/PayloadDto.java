package com.example.subscriber_with_integration.dto;

import java.util.Objects;

public class PayloadDto {
  private String message;

  public PayloadDto(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public PayloadDto() {
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    var that = (PayloadDto) obj;
    return Objects.equals(this.message, that.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }

  @Override
  public String toString() {
    return "PayloadDto[" +
        "message=" + message + ']';
  }

}
