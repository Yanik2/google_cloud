package com.example.publisher.dto;

import java.util.Objects;

public class MessageDto<T> {
  private T payload;
  private String messageMetadata;
  private String type;

  public MessageDto(T payload, String messageMetadata) {
    this.payload = payload;
    this.messageMetadata = messageMetadata;
  }

  public MessageDto() {
  }

  public T getPayload() {
    return payload;
  }

  public void setPayload(T payload) {
    this.payload = payload;
  }

  public String getMessageMetadata() {
    return messageMetadata;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setMessageMetadata(String messageMetadata) {
    this.messageMetadata = messageMetadata;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageDto<?> that = (MessageDto<?>) o;
    return Objects.equals(payload, that.payload) &&
        Objects.equals(messageMetadata, that.messageMetadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(payload, messageMetadata);
  }
}
