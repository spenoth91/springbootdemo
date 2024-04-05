package org.spenoth.springbootdemo.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_returnsSavedUser() {
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

    @Test
    void UserRepository_testFindAll() {
        User u1 = User.builder()
                .name("Test test")
                .email("test@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();
        User u2 = User.builder()
                .name("Test test")
                .email("test1@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();

        userRepository.saveAll(List.of(u1, u2));
        List<User> toTest = userRepository.findAll();
        Assertions.assertThat(toTest).isNotNull();
        Assertions.assertThat(toTest.size()).isEqualTo(2);
    }


    @Test
    void UserRepository_testFindById() {
        User u1 = User.builder()
                .name("Test test")
                .email("test@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();
        User u2 = User.builder()
                .name("Test test")
                .email("test1@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();

        userRepository.saveAll(List.of(u1, u2));
        Optional<User> toTest = userRepository.findById(u1.getId());
        Assertions.assertThat(toTest.isPresent()).isTrue();
        Assertions.assertThat(toTest.get().getId()).isEqualTo(u1.getId());
    }

    @Test
    void UserRepository_testFindBlocked() {
        User u1 = User.builder()
                .name("Test test")
                .email("test@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .blocked(true)
                .build();

        User u2 = User.builder()
                .name("Test test")
                .email("test1@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();

        User u3 = User.builder()
                .name("Test test")
                .email("test3@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .blocked(true)
                .build();

        userRepository.saveAll(List.of(u1, u2,u3));
        List<User> toTest = userRepository.findBlocked();
        Assertions.assertThat(toTest).isNotNull();
        Assertions.assertThat(toTest.size()).isEqualTo(2);
    }
}