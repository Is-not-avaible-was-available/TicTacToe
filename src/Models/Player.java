package Models;

import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private long id;

    public Player(String name, Symbol symbol, PlayerType playerType, long id){
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.id= id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Move makeMove(Board board){
        //ask the player to provide the index to make a move
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please tell the row index to make a move");
        int row = scanner.nextInt();
        System.out.println("Please tell the col index to make a move");
        int col = scanner.nextInt();
        return new Move(new Cell(row, col), this);

}
}
