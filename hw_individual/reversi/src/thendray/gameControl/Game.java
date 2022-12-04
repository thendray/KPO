package thendray.gameControl;

import thendray.models.Cell;
import thendray.models.GameBoard;
import thendray.models.GameBoardHistory;
import thendray.models.chip.ChipTypes;
import thendray.models.player.ComputerPlayer;
import thendray.models.player.Player;
import thendray.models.player.RealPlayer;



import static thendray.tools.InputInformation.*;

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
        if (isChipTypeCircle) {
            player1 = new RealPlayer(name, ChipTypes.Circle);
            if (choice.equals("2")) {
                player2 = new RealPlayer(name2, ChipTypes.Square);
            } else {
                player2 = new ComputerPlayer(ChipTypes.Square);
            }
        } else {
            player1 = new RealPlayer(name, ChipTypes.Square);
            if (choice.equals("2")) {
                player2 = new RealPlayer(name2, ChipTypes.Circle);
            } else {
                player2 = new ComputerPlayer(ChipTypes.Circle);
            }
        }

        gameBoard = new GameBoard();
        gameBoardHistory = new GameBoardHistory();
        isPlayer1Turn = true;
    }

    public void startGame() {
        Cell newChosenCell = null;

        while (gameBoard.isNotFull() && !gameBoard.isOneTypeOfChipOnBoard()) {
            System.out.println();
            System.out.println(gameBoard);
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
                if (!(player2.isComputer() && !isPlayer1Turn)) {
                    gameBoardHistory.addNewGameBoardCondition(gameBoard);
                }
                gameBoard.putChipOnCell(newChosenCell);
            } else {
                System.out.println("\nХодов больше нет!");
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
        System.out.println("\nРезультаты игры: ");

        int countPlayer1Chip = 0;
        int countPlayer2Chip = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard.getBoardCells()[i][i].getChip().getType() == player1.getChipType()) {
                    countPlayer1Chip += 1;
                } else if (gameBoard.getBoardCells()[i][i].getChip().getType() == player2.getChipType()) {
                    countPlayer2Chip += 1;
                }
            }
        }

        System.out.println(String.format("Игрок %s набрал %d очк.", player1.getName(), countPlayer1Chip));
        System.out.println(String.format("Игрок %s набрал %d очк.", player2.getName(), countPlayer2Chip));
        System.out.print("И победу одерживает ... ");

        if (countPlayer1Chip >= countPlayer2Chip) {
            System.out.println(player1.getName());
        } else {
            System.out.println(player2.getName());
        }

        return Math.max(countPlayer1Chip, countPlayer2Chip);
    }
}
