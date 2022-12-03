package reversi.tools;

import reversi.models.Cell;
import reversi.models.chip.Chip;
import reversi.models.chip.ChipTypes;

import java.util.ArrayList;
import java.util.List;

public class LockedCells {

    public static boolean hasAnyLockedCells() {
        return true;
    }

    public static List<Cell> getLockedCells(Cell cell, ChipTypes chipType, Cell[][] boardCells) {
        List<Cell> lockedCells = new ArrayList<>();

        int x = cell.getX();
        int y = cell.getY();
        while (x > -1) {
            x -= 1;
            Cell cellAbove = boardCells[x][y];
            if (cellAbove.getChip().getType() == chipType) {

            }
        }
    }
}
