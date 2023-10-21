package com.spring.aws.config;

import com.spring.aws.domain.Character;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class AwsLambdaConfig {
    @Bean
    public Filter getFilter() {
        return new SecurityFilter();
    }

    @Bean(name = "Saludar")
    public Supplier<String> greeting() {
        return () -> "Hello world";
    }

    @Bean
    public Consumer<String> printParam() {
        return (param) -> {
            System.out.println(param);
        };
    }

    @Bean
    public Function<String, String> receiveParam() {
        return (param) -> {
            String name = param.toUpperCase();
            return name;
        };
    }

    // Gerate a JSON
    @Bean
    public Supplier<Map<String, Object>> createCharacter() {
        return () -> {
            Map<String, Object> character = new HashMap<>();
            character.put("name", "Goku");
            character.put("healthPoints", 100);
            character.put("skill", "Kame Hame Ha!");
            return character;
        };
    }

    // Recibir un JSON and return String
    @Bean
    public Function<Map<String, Object>, String> receiveCharacter() {
        return (param) -> {
            param.forEach((key, value) -> System.out.println(key + " - " + value.toString()));
            return "Personaje recibido";
        };
    }

    // Receive an OBJECT and return an OBJECT
    @Bean
    public Function<Character, Character> receiveAnObject() {
        return (param) -> param;
    }

    // Receive a JSON and return an OBJECT
    @Bean
    public Function<Map<String, Object>, Map<String, Object>> processCharacters() {
        return (param) -> {
            Map<String, Object> mapCharacter = param;
            mapCharacter.forEach((key, value) -> System.out.println(key + " - " + value.toString()));
            Map<String, Object> newCharacter = new HashMap<>();
            newCharacter.put("name", "Krillin");
            newCharacter.put("healthPoints", 50);
            newCharacter.put("skills", new String[]{"Ki En Zai", "Kame Hame Ha!"});
            return newCharacter;
        };
    }
}
