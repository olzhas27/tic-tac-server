package space.testapp.tictacserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import space.testapp.tictacserver.game.PlayerRole;

@Data
@NoArgsConstructor
public class Cell {
    @JsonProperty("value")
    private PlayerRole value;

    @JsonProperty("position")
    private int position;

    public Cell(int position) {
        value = null;
        this.position = position;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return value == null;
    }

    public boolean setValue(PlayerRole value) {
        if (this.value == null && value != null) {
            this.value = value;
            return true;
        }
        return false;
    }
}
