package com.example.subscriber_with_integration.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Payload.class, name = "payload"),
    @JsonSubTypes.Type(value = Parser.class, name = "parser")
})
public abstract class MessageDto<T> {
  private T payload;
  private String messageMetadata;
  private String type;


  public MessageDto(T payload, String messageMetadata, String type) {
    this.payload = payload;
    this.messageMetadata = messageMetadata;
    this.type = type;
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

  public void setMessageMetadata(String messageMetadata) {
    this.messageMetadata = messageMetadata;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageDto that = (MessageDto) o;
    return Objects.equals(payload, that.payload) &&
        Objects.equals(messageMetadata, that.messageMetadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(payload, messageMetadata);
  }
}
