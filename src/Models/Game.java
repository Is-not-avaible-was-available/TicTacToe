package Models;

import Exceptions.GameInvalidException;
import Strategies.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;

    private List<Player> playerList;

    private List<Move> moveList;

    private GameStatus gameStatus;

    private Player playerWinner;

    private int nextPlayerMoveIndex;
    private List<WinningStrategy> winningStrategyList;


    private Game(int dimension, List<Player>playerList, List<WinningStrategy> winningStrategyList){

        this.board = new Board(dimension);
        this.playerList = playerList;
        this.winningStrategyList = winningStrategyList;
        this.moveList = new ArrayList<>();
        this.nextPlayerMoveIndex = 0;
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Move> getMoveList() {
        return moveList;
    }

    public void setMoveList(List<Move> moveList) {
        this.moveList = moveList;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getPlayerWinner() {
        return playerWinner;
    }

    public void setPlayerWinner(Player playerWinner) {
        this.playerWinner = playerWinner;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public void printBoard(){
        board.displayBoard();

    }

    public void  undo(){
       Move move = moveList.remove(moveList.size()-1);
       Cell cell = move.getCell();
       cell.setCellState(CellState.EMPTY);
       nextPlayerMoveIndex -=1;
    }

    private boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col =move.getCell().getCol();

        if(row>board.getSize() | row < 0 || col>board.getSize()|| col<0 ||
                board.getBoard().get(row).get(col).getCellState()==CellState.FILLED){
            return false;
        }
        return true;

    }

    private boolean checkWinner(Board board, Move move){
        for(WinningStrategy winningStrategy : winningStrategyList){
            if(winningStrategy.checkWinner(move, board)){
                return true;
            }
        }
        return false;

    }
    public void  makeMove(Board board){
        Player currentPlayer = playerList.get(nextPlayerMoveIndex);
        System.out.println("It is "+currentPlayer.getName() + " 's move.");

        Move move = currentPlayer.makeMove(board);
        System.out.println(currentPlayer.getName() + " has made a move at Row: " + move.getCell().getRow() + "" +
                ", Col: " + move.getCell().getCol());


        //Validate the move, before we apply to the board

        if(!validateMove(move)){
            System.out.println("Invalid move by player "+ currentPlayer.getName());
            return;

        }
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell finalCellToMakeAMove = board.getBoard().get(row).get(col);
        finalCellToMakeAMove.setCellState(CellState.FILLED);
        finalCellToMakeAMove.setPlayer(currentPlayer);
        Move finalMove = new Move(finalCellToMakeAMove, currentPlayer);
        moveList.add(finalMove);

        nextPlayerMoveIndex+=1;
        nextPlayerMoveIndex%=playerList.size();

        //once player has made a move, check the winner
        //checkwinner

        if(checkWinner(board, finalMove)){
            gameStatus = GameStatus.ENDED;
            playerWinner = currentPlayer;
        }else if(moveList.size()==board.getSize() * board.getSize()){
            gameStatus = GameStatus.DRAW;

        }



    }
    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        private Board board;
        private int dimension;
        private List<Player> playerList;
        private List<WinningStrategy> winningStrategyList;

//        private Builder(){
//            this.dimension = 0;
//            this.playerList = new ArrayList<>();
//            this.winningStrategyList = new ArrayList<>();
//            this.board = new Board(dimension);
//        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public Builder setPlayerList(List<Player> playerList) {
            this.playerList = playerList;
            return this;
        }


        public Builder setWinningStrategyList(List<WinningStrategy> winningStrategyList) {
            this.winningStrategyList = winningStrategyList;
            return this;
        }

        public boolean validate(){

            //Validations
            //1 bot per game
            //No two players should have the same symbol
            int count = 0;
            for(int i=0;i<playerList.size();i++){

                if(playerList.get(i).equals(PlayerType.BOT)){
                    count++;
                }
            }
            if(count>1){
                return false;
            }

            Set<Character> set = new HashSet<>();
            for(int i=0;i<playerList.size();i++){
                Character symbol = playerList.get(i).getSymbol().getSymbol();
                if(set.contains(symbol)){
                    return false;
                }else{
                    set.add(symbol);
                }
            }

            return true;
        }
        public Game build() throws Exception {
            //Validate
            //Create the game object

            if(!validate()){
                throw new GameInvalidException("Invalid Game!");
            }
            return new Game(dimension, playerList,winningStrategyList);
        }
    }
}
