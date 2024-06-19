package models;

import enums.GameState;
import enums.PlayerType;
import lombok.Data;
import strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private int nextPlayerIndex;
    private Player winner;
    private GameState gameState;
    private List<WinningStrategy> winningStrategies;

    private Game(Builder builder){
        if(builder.dimensions < 3) throw new IllegalArgumentException("dimensions must be at least 3");

        validatePlayers(builder.players);

        this.board = new Board(builder.dimensions);
        this.players = builder.players;
        this.winningStrategies = builder.winningStrategies;
        this.moves = new ArrayList<>();
        this.nextPlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
    }

    private static void validatePlayers(List<Player> players){
        Set<Character> characterSet = new HashSet<>();
        int numOfBots = 0;
        for(Player player : players){
            characterSet.add(player.getSymbol());
            if(player.getPlayerType().equals(PlayerType.BOT)) numOfBots++;
        }

        if(numOfBots > 1 || characterSet.size() != players.size()){
            throw new IllegalArgumentException("bots cannot be more than 1 and symbol must be different for all the players");
        }
    }

    private static class Builder{
        private List<Player> players;
        private int dimensions;
        private List<WinningStrategy> winningStrategies;

        private Builder(){
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
            this.dimensions = 3;
        }

        public Builder setPlayer(List<Player> players){
            this.players = players;
            return this;
        }

        public Builder setWinningStrategy(List<WinningStrategy> winningStrategies){
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimensions(int dimensions){
            this.dimensions = dimensions;
            return this;
        }

        public Game build(){
            return new Game(this);
        }
    }
}
