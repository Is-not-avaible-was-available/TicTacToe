package Models;

import Strategies.BotPlayingStrategy.BotPlayingStrategy;

public class Bot extends Player{

    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;
    public Bot(String name, Symbol symbol, PlayerType playerType, long id, BotPlayingStrategy botPlayingStrategy, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol, playerType, id);
        this.botPlayingStrategy = botPlayingStrategy;
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public BotPlayingStrategy getBotPlayingStrategy() {
        return botPlayingStrategy;
    }

    public void setBotPlayingStrategy(BotPlayingStrategy botPlayingStrategy) {
        this.botPlayingStrategy = botPlayingStrategy;
    }

    @Override
    public Move makeMove(Board board) {
        //How bot will make a move
        //Bot will make a move based on it's botDifficultyLevel
        Move move = botPlayingStrategy.makeMove(board);
       move.setPlayer(this);
       return move;
    }
}
