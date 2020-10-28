package melo.guilherme.rooms.api.room;

import melo.guilherme.rooms.api.user.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.UUID;

public class RoomRequest {
    private UUID id;

    @NotNull
    @Size(min = 10, message = "Nome deve ter pelo menos 10 caracteres")
    @Size(max = 20, message = "Nome deve ter no máximo 20 caracteres")
    private String name;

    @NotNull
    @Size(min = 20, message = "Descrição deve ter pelo menos 20 caracteres")
    @Size(max = 100, message = "Descrição deve ter no máximo 100 caracteres")
    private String description;

    @Min(1)
    @Positive
    @NotNull(message = "Necessário informar a quantidade de pessoas")
    private Integer amountPeople;

    @NotNull(message = "Deve haver um usuário associado à sala")
    private UUID userId;

    public RoomRequest() {
    }

    public Room toModel() {
        return new Room.builder()
                .id(id)
                .name(name)
                .description(description)
                .amountPeople(amountPeople)
                .user(new User.builder().id(userId).build())
                .build();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAmountPeople() {
        return amountPeople;
    }

    public UUID getUserId() {
        return userId;
    }
}
