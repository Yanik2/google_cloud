package com.example.subscriber.config;

import com.example.subscriber.receiver.MessageReceiverImpl;
import com.google.api.gax.batching.FlowControlSettings;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.MessageReceiverWithAckResponse;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.pubsub.v1.ProjectSubscriptionName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.threeten.bp.Duration;

@Configuration
public class SubscriberConfiguration {

  @Value("${cloud.project-id}")
  private String projectId;

  @Value("${cloud.subscription}")
  private String subscription;

  @Bean
  public Subscriber subscriber() {
    final var projectSubscriptionName = ProjectSubscriptionName
        .newBuilder()
        .setProject(projectId)
        .setSubscription(subscription)
        .build();

    final var subscriber = Subscriber.newBuilder(projectSubscriptionName, messageReceiver())
        //by default it sets for 1 hour
        //in case when ack() or nack() weren't called this might be helpful
        .setMaxAckExtensionPeriod(Duration.ofSeconds(3))
        .build();

    subscriber
        .startAsync()
        .awaitRunning();

    return subscriber;
  }

  @Bean
  public MessageReceiverWithAckResponse messageReceiver() {
    return new MessageReceiverImpl();
  }
}
