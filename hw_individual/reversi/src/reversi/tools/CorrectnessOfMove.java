package reversi.tools;

import reversi.models.Cell;
import reversi.models.GameBoard;
import reversi.models.chip.Chip;
import reversi.models.chip.ChipTypes;

import static reversi.tools.NeighboursCells.checkAroundOppositeTypeCells;

public class CorrectnessOfMove {

    public static boolean checkTheCorrectnessOfMove(Cell cell, Cell[][] boardCells) {

        Chip chip = cell.getChip();
        ChipTypes chipType = chip.getType();

        if (chipType != ChipTypes.Empty) {
            return false;
        }

        boolean isNeighboursOppositeType = checkAroundOppositeTypeCells(cell, boardCells);

        if (isNeighboursOppositeType) {

            if (checkIsAnyNeighboursLocked(cell, boardCells)) {
                return true;
            }
        }
        return false;
    }

}
