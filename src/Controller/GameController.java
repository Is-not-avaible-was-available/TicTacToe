package Controller;

import Models.Game;
import Models.GameStatus;
import Models.Player;
import Strategies.WinningStrategy.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategyList) throws Exception {

        Game game = Game.getBuilder()
                .setDimension(dimension)
                .setPlayerList(players)
                .setWinningStrategyList(winningStrategyList)
                .build();
        return game;
    }

    public void makeMove(Game game){
        game.makeMove(game.getBoard());

    }

    public void displayBoard(Game game){
        game.printBoard();

    }
    public Player getWinner(Game game){
        return game.getPlayerWinner();
    }

    public void Undo(Game game){
        game.undo();
    }
    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }
}
