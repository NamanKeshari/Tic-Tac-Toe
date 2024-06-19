package models;

import enums.CellState;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Board {
    private List<List<Cell>> cells;

    private List<Map<Player, Integer>> rowsMapping;
    private List<Map<Player, Integer>> colsMapping;

    public Board(int dimensions){
        this.cells = new ArrayList<>();
        this.rowsMapping = new ArrayList<>();
        this.colsMapping = new ArrayList<>();

        for(int i = 0; i < dimensions; i++){
            this.rowsMapping.add(new HashMap<>());
            this.colsMapping.add(new HashMap<>());
        }

        for(int i = 0; i < dimensions; i++){
            this.cells.add(new ArrayList<>());
            for(int j = 0; j < dimensions; j++){
                this.cells.get(i).add(new Cell(i, j));
            }
        }
    }

    public void displayBoard(){
        for (List<Cell> cells : this.cells) {
            for (Cell cell : cells) {
                if (cell.getCellState().equals(CellState.FREE)) {
                    System.out.print("| |");
                } else {
                    System.out.printf("| %s | ", cell.getPlayer().getSymbol());
                }
            }
            System.out.println();
        }
    }
}
