package com.example.subscriber_streams.config;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.messaging.support.MessageBuilder;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Configuration
public class SubscriberConfig {

  @Bean
  public MessageChannel messageChannel() {
    return new PublishSubscribeChannel();
  }

  @Bean
  public PubSubInboundChannelAdapter pubSubInboundChannelAdapter(PubSubTemplate pubSubTemplate) {
    final var adapter = new PubSubInboundChannelAdapter(pubSubTemplate,
        "test_subscription");

    adapter.setOutputChannel(messageChannel());
    adapter.setPayloadType(String.class);
    adapter.setAckMode(AckMode.MANUAL);
    return adapter;
  }

  @Bean
  public Consumer<Message<String>> receiveMessage() {
    return message -> {
      System.out.println(message.getPayload());
    };
  }

  @Bean
  public Supplier<Flux<Message<String>>> sendMessage() {
    return () ->
        Flux.<Message<String>>generate(
            sink -> {
              try {
                Thread.sleep(10000);
              } catch (InterruptedException e) {

              }
              Message<String> message = MessageBuilder.withPayload("message").build();
              sink.next(message);
            }
        ).subscribeOn(Schedulers.boundedElastic());
  }
}
