package com.example.subscriber_with_integration.config;

import com.example.subscriber_with_integration.dto.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class SubscriberConfig {

  @Bean
  public PubSubInboundChannelAdapter pubSubInboundChannelAdapter(
    PubSubTemplate pubSubTemplate
  ) {
    final var adapter = new PubSubInboundChannelAdapter(pubSubTemplate, "projects/local-project/subscriptions/local-subscription");
    adapter.setOutputChannel(messageChannel());
    adapter.setPayloadType(MessageDto.class);

    adapter.setAckMode(AckMode.MANUAL);
    return adapter;
  }

  @Bean
  public MessageChannel messageChannel() {
    return new DirectChannel();
  }

  @Bean
  public JacksonPubSubMessageConverter jacksonPubSubMessageConverter(ObjectMapper objectMapper) {
    return new JacksonPubSubMessageConverter(objectMapper);
  }
}
