package com.example.subscriber_with_integration.receiver;

import static com.google.cloud.spring.pubsub.support.GcpPubSubHeaders.ORIGINAL_MESSAGE;

import com.example.subscriber_with_integration.dto.MessageDto;
import com.example.subscriber_with_integration.dto.PayloadDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {
  private final ObjectMapper objectMapper;

  public MessageReceiver(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }


  @ServiceActivator(inputChannel = "messageChannel")
  public void receiveMessage(MessageDto<?> messageDto) {

    messageDto.getMessageMetadata();
  }
}
