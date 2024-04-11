package org.spenoth.springbootdemo.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Entity
@Table(name = "t_user")
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Setter
    private String password;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private boolean blocked;

    @Getter
    @Setter
    private LocalDate dob;

    @Enumerated(EnumType.STRING) //Tell spring that it's a enum
    private Role role;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
