package org.spenoth.springbootdemo.user;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveValidUser_returnsSavedUser() {
        //Arrange
        User u = User.builder()
                .name("Test test")
                .email("test@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();


        //Act
        User saved = userRepository.save(u);

        //Assert
        Assertions.assertThat(saved ).isNotNull();
        Assertions.assertThat(saved.getId()).isGreaterThan(0);
    }
}
