package melo.guilherme.rooms.api.room;

import com.fasterxml.jackson.databind.ObjectMapper;
import melo.guilherme.rooms.api.user.User;
import melo.guilherme.rooms.api.user.UserRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
// cria application context com apenas os beans necessários pra testar a camada do controller
@WebMvcTest(controllers = RoomController.class, useDefaultFilters = true)
// sobe o contexto total da aplicação
// @AutoConfigureMockMvc
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoomRepository repository;

    @MockBean
    private UserRepository userRepository;

    private static final String URI = "/v1/rooms";

    @Test
    public void shouldGetAllRooms() throws Exception {

        Mockito.when(repository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get(URI))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotGetRoomWhenItDoesNotExist() throws Exception {

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        final String id = UUID.randomUUID().toString();

        mockMvc.perform(get(URI + "/" + id))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages[0].message", Matchers.equalTo("Sala não encontrada!")));
    }

    @Test
    public void shouldGetRoom() throws Exception {



        User user = new User.builder().id(UUID.randomUUID()).build();
        Room room = new Room.builder()
                .id(UUID.randomUUID())
                .name("Sala 1 do andar 1")
                .description("Sala com ar condicionado, televisão e geladeira")
                .amountPeople(5)
                .user(user)
                .build();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(room));
        final String id = UUID.randomUUID().toString();

        mockMvc.perform(get(URI + "/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldSaveRoom() throws Exception {
        User user = new User.builder().id(UUID.randomUUID()).build();
        RoomRequest request = new RoomRequest("Sala 1 do andar 1", "Sala com ar condicionado, televisão e geladeira", 5, user.getId());

        Room room = new Room.builder()
                            .id(UUID.randomUUID())
                            .name("Sala 1 do andar 1")
                            .description("Sala com ar condicionado, televisão e geladeira")
                            .amountPeople(5)
                            .user(user)
                            .build();

        Mockito.when(repository.save(Mockito.any())).thenReturn(room);
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        mockMvc.perform(post(URI).content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldNotSaveRoomWhenUserIsNull() throws Exception {
        User user = new User.builder().id(UUID.randomUUID()).build();
        RoomRequest request = new RoomRequest("Sala 1 do andar 1", "Sala com ar condicionado, televisão e geladeira", 5, null);

        Room room = new Room.builder()
                .id(UUID.randomUUID())
                .name("Sala 1 do andar 1")
                .description("Sala com ar condicionado, televisão e geladeira")
                .amountPeople(5)
                .user(user)
                .build();

        Mockito.when(repository.save(Mockito.any())).thenReturn(room);
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        mockMvc.perform(post(URI).content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages[0].message", Matchers.equalTo("Deve haver um usuário associado à sala")));
    }

    @Test
    public void shouldNotSaveRoomWhenUserDoesNotExist() throws Exception {
        User user = new User.builder().id(UUID.randomUUID()).build();
        RoomRequest request = new RoomRequest("Sala 1 do andar 1", "Sala com ar condicionado, televisão e geladeira", 5, user.getId());

        Room room = new Room.builder()
                .id(UUID.randomUUID())
                .name("Sala 1 do andar 1")
                .description("Sala com ar condicionado, televisão e geladeira")
                .amountPeople(5)
                .user(user)
                .build();

        Mockito.when(repository.save(Mockito.any())).thenReturn(room);
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        mockMvc.perform(post(URI).content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages[0].message", Matchers.equalTo("Usuário associado à sala não existe!")));
    }


    @Test
    public void shouldUpdateRoom() throws Exception {
        User user = new User.builder().id(UUID.randomUUID()).build();
        RoomRequest request = new RoomRequest("Sala 1 do andar 2", "Sala com ar condicionado, televisão e geladeira", 5, user.getId());

        Room room = new Room.builder()
                .id(UUID.randomUUID())
                .name("Sala 1 do andar 1")
                .description("Sala com ar condicionado, televisão e geladeira")
                .amountPeople(5)
                .user(user)
                .build();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.ofNullable(room));
        Mockito.when(repository.save(Mockito.any())).thenReturn(room);

        mockMvc.perform(put(URI + "/" + room.getId().toString()).content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteRoom() throws Exception {
        final String id = UUID.randomUUID().toString();

        mockMvc.perform(delete(URI + "/" + id))
                .andExpect(status().isOk());

    }

}
