package Strategies.WinningStrategy;

import Models.Board;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy{
    private Map<Integer, Map<Symbol, Integer>> colMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, Board board) {
        int col= move.getCell().getCol();

        Symbol symbol = move.getPlayer().getSymbol();

        if(!colMaps.containsKey(col)){
            colMaps.put(col, new HashMap<>());
        }

        Map<Symbol, Integer> currMap = colMaps.get(col);
        if(currMap.containsKey(symbol)){
            currMap.put(symbol, currMap.get(symbol) + 1);
        }else{
            currMap.put(symbol,1);
        }

        return currMap.get(symbol) == board.getSize();

    }

}
