package com.example.subscriber.receiver;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.AckReplyConsumerWithResponse;
import com.google.cloud.pubsub.v1.MessageReceiverWithAckResponse;
import com.google.pubsub.v1.PubsubMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//MessageReceiver interface provides AckReplyConsumer without response
//public class MessageReceiverImpl implements MessageReceiver {
public class MessageReceiverImpl implements MessageReceiverWithAckResponse {

  private static final Logger logger = LoggerFactory.getLogger(MessageReceiverImpl.class.getName());

  //  @Override
  public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
    final var data = message.getData().toString();
    System.out.println(data);
    logger.info(data);
//    consumer.ack();
//    consumer.nack();
  }

  @Override
  public void receiveMessage(PubsubMessage message, AckReplyConsumerWithResponse consumer) {
    final var data = message.getData().toString();
    System.out.println(data);
    logger.info(data);
//    consumer.ack();
//    consumer.nack();
//      throw new RuntimeException("ex");
  }
}
