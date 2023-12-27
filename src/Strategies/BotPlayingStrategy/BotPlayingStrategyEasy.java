package Strategies.BotPlayingStrategy;

import Models.Board;
import Models.Cell;
import Models.CellState;
import Models.Move;
import java.util.List;

public class BotPlayingStrategyEasy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {

        //Bot will make a move at the first available cell

        for(List<Cell> row : board.getBoard()){
            for(Cell cell : row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    //Bot can make a move here
                    return new Move(cell, null);
                }
            }
        }

        return null;
    }
}
