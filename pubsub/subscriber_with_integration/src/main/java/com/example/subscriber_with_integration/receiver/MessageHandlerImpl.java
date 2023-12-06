package com.example.subscriber_with_integration.receiver;

import com.example.subscriber_with_integration.dto.PayloadDto;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

//@Component
public class MessageHandlerImpl implements MessageHandler {

//  @ServiceActivator(inputChannel = "messageChannel")
  @Override
  public void handleMessage(Message<?> message) throws MessagingException {
    BasicAcknowledgeablePubsubMessage originalMessage =
        message.getHeaders()
            .get(GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class);

    System.out.println(originalMessage.getPubsubMessage());


    final var payload = (PayloadDto) message.getPayload();
    System.out.println(payload);

//    originalMessage.ack();
//    originalMessage.nack();
    throw new MessagingException("failed message processing");
  }
}
