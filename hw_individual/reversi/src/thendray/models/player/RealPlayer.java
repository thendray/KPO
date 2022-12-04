package thendray.models.player;

import thendray.models.Cell;
import thendray.models.chip.ChipTypes;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static thendray.tools.CellsForNextMove.getRightCells;

public class RealPlayer extends Player {
    public RealPlayer(String name, ChipTypes chipType) {
        this.name = name;
        this.chipType = chipType;
        isComputer = false;
    }

    private void printAvailableCells(List<Cell> cells) {
        System.out.print("Клетки, доступные для хода: [");
        List<String> cellsPositionOnBoard = new ArrayList<>();
        for (Cell cell : cells) {
            cellsPositionOnBoard.add(Character.toString('A' + cell.getX()) + (cell.getY() + 1));
        }
        System.out.print(String.join(", ", cellsPositionOnBoard));
        System.out.println("]");
    }

    @Override
    public Cell choseCell(Cell[][] boardCells) {
        Cell cell;
        List<Cell> rightCells = getRightCells(chipType, boardCells);
        if (rightCells.isEmpty()) {
            return null;
        }

        while (true) {
            while (true) {
                String cellChoice;
                printAvailableCells(rightCells);
                System.out.print("\nВыбирете клетку для хода (формат БукваЦифра - А4): ");
                Scanner in = new Scanner(System.in);
                cellChoice = in.nextLine();

                if (cellChoice.length() == 2) {
                    char letter = cellChoice.charAt(0);
                    char number = cellChoice.charAt(1);
                    if (Character.isLetter(letter) && Character.isDigit(number)) {
                        letter = Character.toUpperCase(letter);
                        if (letter >= 'A' && letter <= 'H' && number > '0' && number < '9') {
                            letter -= 'A';
                            number -= '1';
                            cell = new Cell(letter, number, chipType == ChipTypes.Circle);
                            break;
                        }
                    }
                }
                System.out.println("Вы некорректно ввели клетку! Попробуйте снова!");
            }

            boolean isCorrectChoice = false;
            for (Cell rightCell : rightCells) {
                if (rightCell.equals(cell)) {
                    isCorrectChoice = true;
                }
            }

            if (isCorrectChoice) {
                break;
            } else {
                System.out.println("Вы не можете поставить вашу фишку в эту клетку. попробейте снова!");
            }

        }

        return cell;
    }
}
