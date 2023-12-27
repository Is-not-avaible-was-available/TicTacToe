package Strategies.WinningStrategy;

import Models.Board;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{
    private Map<Symbol, Integer> leftDiagonalMap = new HashMap<>();
    private Map<Symbol, Integer> rightDiagonlaMap = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(row==col){
            if(leftDiagonalMap.containsKey(symbol)){
                leftDiagonalMap.put(symbol, leftDiagonalMap.get(symbol)+1);
            }else{
                leftDiagonalMap.put(symbol,1);
            }
        }

        if(row+col==board.getSize()-1){
            if(rightDiagonlaMap.containsKey(symbol)){
                rightDiagonlaMap.put(symbol, rightDiagonlaMap.get(symbol)+1);
            }else{
                rightDiagonlaMap.put(symbol,1);
            }
        }

        if(row==col && leftDiagonalMap.get(symbol)==board.getSize()){
            return true;
        }

        if(row+col==board.getSize()-1 && rightDiagonlaMap.get(symbol)== board.getSize()){
            return true;
        }
        return false;
    }
}
