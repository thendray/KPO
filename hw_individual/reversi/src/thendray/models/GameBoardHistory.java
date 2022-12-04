package thendray.models;

import java.util.ArrayList;
import java.util.List;

public class GameBoardHistory {

    List<GameBoard> boardCellsInOneGame;

    public GameBoardHistory() {
        boardCellsInOneGame = new ArrayList<>();
    }

    public GameBoard returnToLastCondition() {
        if (boardCellsInOneGame.size() > 0) {
            int indexOfLast = boardCellsInOneGame.size() - 1;
            GameBoard lastGameBoard = boardCellsInOneGame.get(indexOfLast);
            boardCellsInOneGame.remove(indexOfLast);
            return lastGameBoard;
        }
        return new GameBoard();
    }

    public void addNewGameBoardCondition(GameBoard gameBoard) {
        boardCellsInOneGame.add(new GameBoard(gameBoard.getBoardCells()));
    }

}
