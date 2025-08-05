package org.example.utils;

import org.example.domain.enums.EventType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PublishDtoEvent {
    EventType eventType();
    Class<?> payloadClass();
    String topic();
}
