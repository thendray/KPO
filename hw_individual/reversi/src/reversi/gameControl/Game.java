package reversi.gameControl;

import reversi.models.Cell;
import reversi.models.GameBoard;
import reversi.models.GameBoardHistory;
import reversi.models.player.ComputerPlayer;
import reversi.models.player.Player;
import reversi.models.player.RealPlayer;


import static reversi.tools.InputInformation.*;

public class Game {

    GameBoard gameBoard;
    Player player1;
    Player player2;
    boolean isPlayer1Turn;

    GameBoardHistory gameBoardHistory;




    public Game() {
        String choice = inputQuantityOfPlayers();
        String name = inputPlayerName("первого");
        String name2 = "";
        if (choice.equals("2")) {
            name2 = inputPlayerName("второго");
        }
        boolean isChipTypeCircle = inputChipType();
        player1 = new RealPlayer(name, isChipTypeCircle);

        if (choice.equals("2")) {
            player2 = new RealPlayer(name2, !isChipTypeCircle);
        } else {
            player2 = new ComputerPlayer(!isChipTypeCircle);
        }

        gameBoard = new GameBoard();
        gameBoardHistory = new GameBoardHistory(gameBoard);
        isPlayer1Turn = true;
    }

    public void startGame() {
        Cell newChosenCell = null;
        System.out.println(gameBoard);

        while (gameBoard.isNotFull() && !gameBoard.isOneTypeOfChipOnBoard()) {
            if (isPlayer1Turn) {
                System.out.println(String.format("\nХод первого игрока. %s ходите!\n", player1.getName()));
            } else {
                if (player2.isComputer()) {
                    System.out.print(String.format("\nХод комрьютера. %s ", player2.getName()));
                } else {
                    System.out.println(String.format("\nХод второго игрока. %s ходите!\n", player2.getName()));
                }
            }

            if (isPlayer1Turn) {
                newChosenCell = player1.choseCell(gameBoard.getBoardCells());
            } else {
                newChosenCell = player2.choseCell(gameBoard.getBoardCells());
                if (player2.isComputer()) {
                    System.out.println(String.format("ходит: %s%d\n",
                            Character.toString('A' + newChosenCell.getX()), newChosenCell.getY() +1));
                }
            }

            if (newChosenCell != null){
                gameBoard.changeCell(newChosenCell);
                if (!(player2.isComputer() && !isPlayer1Turn)) {
                    gameBoardHistory.addNewGameBoardCondition(gameBoard);
                }
            } else {
                break;
            }

            System.out.println(gameBoard);

            if (!(player2.isComputer() && !isPlayer1Turn)) {
                boolean shouldGoBackForOneStep = inputShouldGoBackForOneStep();

                if (shouldGoBackForOneStep) {
                    gameBoard = gameBoardHistory.returnToLastCondition();
                    continue;
                }
            }

            isPlayer1Turn = !isPlayer1Turn;
        }
    }


    public int gameResults() {
        System.out.println("Результаты игры: ");

        int maxPoints = 10;

        return maxPoints;
    }
}
