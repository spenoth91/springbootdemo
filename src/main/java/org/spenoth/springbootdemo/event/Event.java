package org.spenoth.springbootdemo.event;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    Long eventId;

    @Getter
    @Setter
    LocalDateTime eventTs;
    @Getter
    @Setter
    String eventDescription;
}
