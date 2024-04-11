package org.spenoth.springbootdemo.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    @BeforeEach
    public void init() {
        user = User.builder()
                .name("Test bla")
                .email("test@gmail.com")
                .dob(LocalDate.of(1996, 3, 4))
                .build();
    }

//    @Test
//    public void UserController_register_returnOk() throws Exception {
//        given(userService.addUser(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));
//
//        ResultActions response = mockMvc.perform(post("/api/v1/user/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(user)));
//
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(user.getEmail())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(user.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.blocked", CoreMatchers.is(user.isBlocked())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.dob", CoreMatchers.is(user.getDob().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")))))
//                //.andDo(MockMvcResultHandlers.print())
//        ;
//    }
//
//    @Test
//    public void UserController_getAllUser() throws Exception {
//        List<User> resp = List.of(User.builder().id(1L).build(),
//                User.builder().id(2L).build(),
//                User.builder().id(3L).build());
//        when(userService.getUsers()).thenReturn(resp);
//
//        ResultActions response = mockMvc.perform(get("/api/v1/user/getusers")
//                                        .contentType(MediaType.APPLICATION_JSON));
//
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
//                //.andDo(MockMvcResultHandlers.print())
//        ;
//    }
//
//    @Test
//    public void UserController_getUserById() throws Exception {
//        Long uid = 1L;
//        User testUser = User.builder()
//                .id(uid)
//                .name("John Pokemon")
//                .email("test@ghoo.com")
//                .dob(LocalDate.of(1966, 7, 8))
//                .build();
//
//        when(userService.getUser(uid)).thenReturn(Optional.of(testUser));
//
//        ResultActions response = mockMvc.perform(get("/api/v1/user/1")
//                .contentType(MediaType.APPLICATION_JSON));
//
//
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(testUser.getEmail())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(testUser.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.blocked", CoreMatchers.is(testUser.isBlocked())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.dob", CoreMatchers.is(testUser.getDob().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")))))
//              //  .andDo(MockMvcResultHandlers.print())
//        ;
//    }

}
