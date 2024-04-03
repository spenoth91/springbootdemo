package org.spenoth.springbootdemo.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;


@Entity
@Table(name = "t_user")
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private boolean blocked;

    @Getter
    @Setter
    private LocalDate dob;

    @Transient
    private Optional<Integer> age;

    /**
     * Calculate the age in years
     * as the time difference between birthdate and the current date;
     * @return the age or optional.empty if no birthdate is available
     */
    public Optional<Integer> getAge() {
        if (this.dob != null) {
            return Optional.of(Period.between(dob, LocalDate.now()).getYears());
        } else return Optional.empty();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return blocked == user.blocked && id.equals(user.id) && email.equals(user.email) && name.equals(user.name) && dob.equals(user.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, blocked, dob);
    }
}
