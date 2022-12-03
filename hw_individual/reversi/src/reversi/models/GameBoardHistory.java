package reversi.models;

import java.util.ArrayList;
import java.util.List;

public class GameBoardHistory {

    List<GameBoard> gameBoardsInOneGame;

    public GameBoardHistory(GameBoard gameBoard) {
        gameBoardsInOneGame = new ArrayList<>();
        gameBoardsInOneGame.add(gameBoard);
    }

    public GameBoard returnToLastCondition() {
        if (gameBoardsInOneGame.size() > 1) {
            int indexOfLast = gameBoardsInOneGame.size() - 1;
            GameBoard lastGameBoard = gameBoardsInOneGame.get(indexOfLast);
            gameBoardsInOneGame.remove(indexOfLast);
            return lastGameBoard;
        }
        return null;
    }

    public void addNewGameBoardCondition(GameBoard gameBoard) {
        gameBoardsInOneGame.add(gameBoard);
    }

}
