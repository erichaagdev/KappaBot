package com.gorlah.kappabot.function;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BotResponseFormatter {

    public String format(String response, BotRequestMetadata metadata) {
        return StringSubstitutor.replace(response, getPropertyReplacementMap(metadata));
    }

    private Map<String, String> getPropertyReplacementMap(BotRequestMetadata metadata) {
        return getPropertyDescriptorsFromMetadata().stream()
                .filter(property -> !"class".equals(property.getName()))
                .collect(Collectors.toMap(PropertyDescriptor::getName, pd -> getValueForReplacement(pd, metadata)));
    }

    private List<PropertyDescriptor> getPropertyDescriptorsFromMetadata() {
        try {
            return Arrays.asList(Introspector.getBeanInfo(SubstitutableMetadataFields.class).getPropertyDescriptors());
        } catch (IntrospectionException e) {
            log.error(e.getMessage(), e);
        }

        return Collections.emptyList();
    }

    private String getValueForReplacement(PropertyDescriptor propertyDescriptor, BotRequestMetadata metadata) {
        try {
            return (String) propertyDescriptor.getReadMethod().invoke(metadata);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }

        return propertyDescriptor.getName();
    }
}
