package org.spenoth.springbootdemo.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void UserServiceTest_testGetAll()
    {
        User u1 = User.builder()
                .name("Test test")
                .email("test@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();

        User u2 = User.builder()
                .name("Test test")
                .email("test@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();

        User u3 = User.builder()
                .name("Test test")
                .email("test@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();

        when(userRepository.findAll()).thenReturn(List.of(u1,u2,u3));

        List<User> toTest = userService.getUsers();

        Assertions.assertThat(toTest.size()).isEqualTo(3);
    }

    @Test
    public void UserServiceTest_testAddUser_Valid()
    {
        //Arrange
        //input
        User uIn = User.builder()
                .name("Test test")
                .email("test@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();
        User uOut = User.builder()
                .id(1L)
                .name("Test test")
                .email("test@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        when(userRepository.save(Mockito.any(User.class))).thenReturn(uOut);

        // Act
        User u = userService.addUser(uIn);

        //Assert
        Assertions.assertThat(u).isEqualTo(uOut);
    }

    @Test
    public void UserServiceTest_testAddUserInValid_emailExists()
    {
        //Arrange
        //input
        User uIn = User.builder()
                .name("Test test")
                .email("test@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(uIn));

        // Act, assert
        Assertions.assertThatThrownBy(() -> userService.addUser(uIn))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email allready taken");
    }

    @Test
    public void UserServiceTest_testUserInValid_missingId()
    {
        //Arrange
        //input
        User uIn = User.builder()
                .name("Test test")
                .email("test@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();

        // Act, assert
        Assertions.assertThatThrownBy(() -> userService.updateUser(uIn))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing id");
    }

    @Test
    public void UserServiceTest_testUserInValid_missingName()
    {
        //Arrange
        //input
        User uIn = User.builder()
                .id(1L)
                .email("test@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();

        // Act, assert
        Assertions.assertThatThrownBy(() -> userService.updateUser(uIn))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing name");
    }

    @Test
    public void UserServiceTest_testUserInValid_missingEmail()
    {
        //Arrange
        //input
        User uIn = User.builder()
                .id(1L)
                .name("test@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();

        // Act, assert
        Assertions.assertThatThrownBy(() -> userService.updateUser(uIn))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing email");
    }

    @Test
    public void UserServiceTest_updateUserInValid_nuUserWithId()
    {
        User u = User.builder()
                .id(1L)
                .name("Test test")
                .email("test@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();

        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> userService.updateUser(u))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("User with id 1 does not exist.");
    }

    @Test
    public void UserServiceTest_updateUser_noChanges()
    {
        User u = User.builder()
                .id(1L)
                .name("Test test")
                .email("test@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();

        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(u));

        userService.updateUser(u);
    }

    @Test
    public void UserServiceTest_updateUser_validChanges()
    {


        User uIn = User.builder()
                .id(1L)
                .name("Test test")
                .email("test@gmail.com")
                .dob(LocalDate.of(1995,3,4))
                .build();

        User uPers = User.builder()
                .id(1L)
                .name("Test bla")
                .email("test@gmail.com")
                .dob(LocalDate.of(1996,3,4))
                .build();

        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(uPers));

        userService.updateUser(uIn);

    }
}
