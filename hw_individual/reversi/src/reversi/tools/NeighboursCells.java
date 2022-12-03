package reversi.tools;

import reversi.models.Cell;
import reversi.models.GameBoard;
import reversi.models.chip.ChipTypes;

public class NeighboursCells {

    private static ChipTypes getType(Cell[][] boardCells, int x, int y) {
        return boardCells[x - 1][y].getChip().getType();
    }

    public static boolean checkAroundOppositeTypeCells(Cell cell, Cell[][] boardCells) {
        int x = cell.getX();
        int y = cell.getY();
        ChipTypes chipType = cell.getChip().getType();

        ChipTypes chipAbove;
        ChipTypes chipBelow;
        ChipTypes chipLeft;
        ChipTypes chipRight;
        ChipTypes chipAboveLeft;
        ChipTypes chipAboveRight;
        ChipTypes chipBelowLeft;
        ChipTypes chipBelowRight;

        if (x - 1 > -1) {
            chipLeft = getType(boardCells, x - 1, y);
            if (chipLeft != chipType && chipLeft != ChipTypes.Empty) {
                return true;
            }
        }
        if (x + 1 < 8) {
            chipRight = getType(boardCells, x + 1, y);
            if (chipRight != chipType && chipRight != ChipTypes.Empty) {
                return true;
            }
        }
        if (y - 1 > -1) {
            chipAbove = getType(boardCells, x, y - 1);
            if (chipAbove != chipType && chipAbove != ChipTypes.Empty) {
                return true;
            }
        }
        if (y + 1 < 8) {
            chipBelow = getType(boardCells, x, y + 1);
            if (chipBelow != chipType && chipBelow != ChipTypes.Empty) {
                return true;
            }
        }
        if (x - 1 > -1 && y - 1 > -1) {
            chipAboveLeft = getType(boardCells, x - 1, y);
            if (chipAboveLeft != chipType && chipAboveLeft != ChipTypes.Empty) {
                return true;
            }
        }
        if (x + 1 < 8 && y - 1 > -1) {
            chipAboveRight = getType(boardCells, x + 1, y);
            if (chipAboveRight != chipType && chipAboveRight != ChipTypes.Empty) {
                return true;
            }
        }
        if (x - 1 > -1 && y + 1 < 8) {
            chipBelowLeft = getType(boardCells, x, y - 1);
            if (chipBelowLeft != chipType && chipBelowLeft != ChipTypes.Empty) {
                return true;
            }
        }
        if (x + 1 < 8 && y + 1 < 8) {
            chipBelowRight = getType(boardCells, x, y + 1);
            if (chipBelowRight != chipType && chipBelowRight != ChipTypes.Empty) {
                return true;
            }
        }
        return false;
    }
}
