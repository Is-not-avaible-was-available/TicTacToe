import Controller.GameController;
import Models.*;
import Strategies.BotPlayingStrategy.BotPlayingStrategyEasy;
import Strategies.WinningStrategy.ColWinningStrategy;
import Strategies.WinningStrategy.DiagonalWinningStrategy;
import Strategies.WinningStrategy.RowWinningStrategy;
import Strategies.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TicTacToeMain {
    public static void main(String[] args) throws Exception {

        GameController gameController = new GameController();

        int dimension = 3;
        int numberOfPlayers = dimension-1;

        List<Player> players = new ArrayList<>();

        players.add(new Player("Rajat", new Symbol('X'), PlayerType.HUMAN, 1L));
        players.add(new Bot("BotPlayer", new Symbol('O'), PlayerType.HUMAN, 2L, new BotPlayingStrategyEasy(), BotDifficultyLevel.EASY));

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add((new DiagonalWinningStrategy()));


        Game game = gameController.startGame(dimension, players, winningStrategies);

        while(gameController.getGameStatus(game)==GameStatus.IN_PROGRESS){

            //Display the board
            System.out.println("This is the current status of the board.");

            gameController.displayBoard(game);
            gameController.makeMove(game);
        }
        if(gameController.getGameStatus(game)==GameStatus.ENDED){
            gameController.displayBoard(game);
            System.out.println("Winner is " + gameController.getWinner(game).getName());
        }else{
            System.out.println("Game is drawn");
        }
    }
}