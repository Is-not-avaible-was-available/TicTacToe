package Strategies.WinningStrategy;

import Models.Board;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{
    private Map<Integer, Map<Symbol, Integer>> rowMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, Board board) {
        int row= move.getCell().getRow();

        Symbol symbol = move.getPlayer().getSymbol();

        if(!rowMaps.containsKey(row)){
            rowMaps.put(row, new HashMap<>());
        }

        Map<Symbol, Integer> currMap = rowMaps.get(row);
        if(currMap.containsKey(symbol)){
            currMap.put(symbol, currMap.get(symbol) + 1);
        }else{
            currMap.put(symbol,1);
        }

        return currMap.get(symbol) == board.getSize();

    }
}
