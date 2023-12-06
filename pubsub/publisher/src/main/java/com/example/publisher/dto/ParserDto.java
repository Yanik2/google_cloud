package com.example.publisher.dto;

public class ParserDto {
  private String parser;
  private String status;
  private int amount;

  public String getParser() {
    return parser;
  }

  public void setParser(String parser) {
    this.parser = parser;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public ParserDto(String parser, String status, int amount) {
    this.parser = parser;
    this.status = status;
    this.amount = amount;
  }

  public ParserDto() {
  }
}
