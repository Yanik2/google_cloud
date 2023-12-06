package com.example.publisher.controller;

import com.example.publisher.dto.MessageDto;
import com.example.publisher.dto.ParserDto;
import com.example.publisher.dto.PayloadDto;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherController {

  private final Publisher publisher;
  private final PubSubTemplate pubSubTemplate;

  public PublisherController(Publisher publisher, PubSubTemplate pubSubTemplate) {
    this.publisher = publisher;
    this.pubSubTemplate = pubSubTemplate;
  }

  @GetMapping("/publish")
  public String publish(@RequestParam String message) {
    final var data = ByteString.copyFromUtf8(message);

    final var pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

    final var future = publisher.publish(pubsubMessage);

    ApiFutures.addCallback(future, new ApiFutureCallback<String>() {
      @Override
      public void onFailure(Throwable t) {
        System.out.println("Exception occurred: " + t);
      }

      @Override
      public void onSuccess(String result) {
        System.out.println("Successful publish: " + result);
      }
    });

    return "ok";
  }

  @GetMapping("/publish/template")
  public String publishTemplate() {
    final var payload = new PayloadDto("payload-bitch");
    final var message = new MessageDto<PayloadDto>(payload, "metadata");
    message.setType("payload");
    //returns CompletableFuture
    pubSubTemplate.publish("test_topic", message);
    return "ok";
  }

  @GetMapping("/publish/template/parser")
  public String publishTemplateParser() {
    final var parser = new ParserDto("my parser", "failed", 32);
    final var message = new MessageDto<ParserDto>(parser, "metadata");
    message.setType("parser");
    //returns CompletableFuture
    pubSubTemplate.publish("test_topic", message);
    return "ok";
  }
}
