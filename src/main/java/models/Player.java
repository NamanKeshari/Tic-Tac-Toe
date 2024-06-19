package models;

import enums.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
    private int id;
    private String name;
    private Character symbol;
    private PlayerType playerType;


}
