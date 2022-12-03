package reversi.models.player;

import reversi.models.Cell;
import reversi.models.GameBoard;

import java.util.Scanner;

import static reversi.tools.CorrectnessOfMove.checkTheCorrectnessOfMove;

public class RealPlayer extends Player {
    public RealPlayer(String name, boolean isCircleChip) {
        this.name = name;
        this.isCircleChip = isCircleChip;
        isComputer = false;
    }

    @Override
    public Cell choseCell(Cell[][] boardCells) {
        Cell cell;

        while (true) {
            while (true) {
                String cellChoice;
                System.out.print("\nВыбирете клетку для хода (формат БукваЦифра А4): ");
                Scanner in = new Scanner(System.in);
                cellChoice = in.nextLine();

                if (cellChoice.length() == 2) {
                    char letter = cellChoice.charAt(0);
                    char number = cellChoice.charAt(1);
                    if (Character.isLetter(letter) && Character.isDigit(number)) {
                        letter = Character.toUpperCase(letter);
                        if (letter >= 'A' && letter <= 'H' && number > 0 && number < 9) {
                            letter -= 'A';
                            number -= 1;
                            cell = new Cell(letter, number, isCircleChip);
                            break;
                        }
                    }
                }
                System.out.println("Вы некорректно ввели клетку! Попробуйте снова!");
            }

            boolean isCorrect = checkTheCorrectnessOfMove(cell, boardCells);

        }

        return null;
    }
}
