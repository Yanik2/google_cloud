package com.example.publisher.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import com.google.pubsub.v1.TopicName;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfiguration {

  @Value("${cloud.topic-name}")
  private String topicName;

  @Value("${cloud.project-id}")
  private String projectId;

  @Bean
  public Publisher publisher() throws IOException {
    final var topic = TopicName.newBuilder()
        .setTopic(topicName)
        .setProject(projectId)
        .build();

    return Publisher.newBuilder(topic).build();
  }

  @Bean
  public JacksonPubSubMessageConverter jacksonPubSubMessageConverter(ObjectMapper objectMapper) {
    return new JacksonPubSubMessageConverter(objectMapper);
  }
}
