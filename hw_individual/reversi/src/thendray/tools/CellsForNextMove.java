package thendray.tools;

import thendray.models.Cell;
import thendray.models.chip.Chip;
import thendray.models.chip.ChipTypes;

import java.util.ArrayList;
import java.util.List;

import static thendray.tools.LockedCells.getLockedCells;

public class CellsForNextMove {

    private static boolean checkTheCorrectnessOfMove(Cell cell, ChipTypes chipType, Cell[][] boardCells) {

        Chip chip = cell.getChip();

        if (chip.getType() != ChipTypes.Empty) {
            return false;
        }

        boolean isCorrectPlace = !getLockedCells(cell, chipType, boardCells).isEmpty();

        return isCorrectPlace;
    }


    public static List<Cell> getRightCells(ChipTypes chipType, Cell[][] boardCells) {
        List<Cell> rightCells = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkTheCorrectnessOfMove(boardCells[i][j], chipType, boardCells)) {
                    rightCells.add(boardCells[i][j]);
                }
            }
        }

        return rightCells;
    }

}
