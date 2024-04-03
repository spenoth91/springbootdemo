package org.spenoth.springbootdemo;

import org.spenoth.springbootdemo.event.Event;
import org.spenoth.springbootdemo.event.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class StartupConfig {

    @Bean
    CommandLineRunner commandLineRunner(EventRepository eventRepository) {
        return args -> {
            Event startup = new Event();
            startup.setEventTs(LocalDateTime.now());
            startup.setEventDescription("System startup");
            eventRepository.save(startup);
        };
    }
}
