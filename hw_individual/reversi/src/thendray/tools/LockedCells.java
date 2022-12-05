package thendray.tools;

import thendray.models.Cell;
import thendray.models.chip.ChipTypes;

import java.util.ArrayList;
import java.util.List;

public class LockedCells {

    public static List<Cell> getLockedCells(Cell cell, ChipTypes chipType, Cell[][] boardCells) {
        List<Cell> lockedCells = new ArrayList<>();
        List<Cell> probablyLockedCells = new ArrayList<>();

        int x = cell.getX();
        int y = cell.getY();

        // замыкание клеток сверху над данной
        while (y > 0) {
            y -= 1;
            Cell cellAbove = boardCells[y][x];
            if (cellAbove.getChip().getType() == chipType) {
                for (Cell lockedCell : probablyLockedCells) {
                    lockedCells.add(lockedCell);
                }
                break;
            } else if (cellAbove.getChip().getType() != ChipTypes.Empty) {
                probablyLockedCells.add(cellAbove);
            } else {
                break;
            }
        }
        y = cell.getY();
        probablyLockedCells = new ArrayList<>();

        // замыкание клеток снизу над данной
        while (y < 7) {
            y += 1;
            Cell cellBellow = boardCells[y][x];
            if (cellBellow.getChip().getType() == chipType) {
                for (Cell lockedCell : probablyLockedCells) {
                    lockedCells.add(lockedCell);
                }
                break;
            } else if (cellBellow.getChip().getType() != ChipTypes.Empty) {
                probablyLockedCells.add(cellBellow);
            } else {
                break;
            }
        }
        y = cell.getY();
        probablyLockedCells = new ArrayList<>();

        // замыкание клеток слева от данной
        while (x > 0) {
            x -= 1;
            Cell cellLeft = boardCells[y][x];
            if (cellLeft.getChip().getType() == chipType) {
                for (Cell lockedCell : probablyLockedCells) {
                    lockedCells.add(lockedCell);
                }
                break;
            } else if (cellLeft.getChip().getType() != ChipTypes.Empty) {
                probablyLockedCells.add(cellLeft);
            } else {
                break;
            }
        }
        x = cell.getX();
        probablyLockedCells = new ArrayList<>();

        // замыкание клеток справа от данной
        while (x < 7) {
            x += 1;
            Cell cellRight = boardCells[y][x];
            if (cellRight.getChip().getType() == chipType) {
                for (Cell lockedCell : probablyLockedCells) {
                    lockedCells.add(lockedCell);
                }
                break;
            } else if (cellRight.getChip().getType() != ChipTypes.Empty) {
                probablyLockedCells.add(cellRight);
            } else {
                break;
            }
        }
        x = cell.getX();
        probablyLockedCells = new ArrayList<>();


        // замыкание клеток сверху-слева
        while (y > 0 && x > 0) {
            x -= 1;
            y -= 1;
            Cell cellAboveLeft = boardCells[y][x];
            if (cellAboveLeft.getChip().getType() == chipType) {
                for (Cell lockedCell : probablyLockedCells) {
                    lockedCells.add(lockedCell);
                }
                break;
            } else if (cellAboveLeft.getChip().getType() != ChipTypes.Empty) {
                probablyLockedCells.add(cellAboveLeft);
            } else {
                break;
            }
        }
        x = cell.getX();
        y = cell.getY();
        probablyLockedCells = new ArrayList<>();


        // замыкание клеток сверху-справа
        while (y > 0 && x < 7) {
            y -= 1;
            x += 1;
            Cell cellAboveRight = boardCells[y][x];
            if (cellAboveRight.getChip().getType() == chipType) {
                for (Cell lockedCell : probablyLockedCells) {
                    lockedCells.add(lockedCell);
                }
                break;
            } else if (cellAboveRight.getChip().getType() != ChipTypes.Empty) {
                probablyLockedCells.add(cellAboveRight);
            } else {
                break;
            }
        }
        x = cell.getX();
        y = cell.getY();
        probablyLockedCells = new ArrayList<>();

        // замыкание клеток снизу-слева
        while (x > 0 && y < 7) {
            x -= 1;
            y += 1;
            Cell cellBellowLeft = boardCells[y][x];
            if (cellBellowLeft.getChip().getType() == chipType) {
                for (Cell lockedCell : probablyLockedCells) {
                    lockedCells.add(lockedCell);
                }
                break;
            } else if (cellBellowLeft.getChip().getType() != ChipTypes.Empty) {
                probablyLockedCells.add(cellBellowLeft);
            } else {
                break;
            }
        }
        x = cell.getX();
        y = cell.getY();
        probablyLockedCells = new ArrayList<>();

        // замыкание клеток снизу-справа
        while (x < 7 && y < 7) {
            x += 1;
            y += 1;
            Cell cellBellowRight = boardCells[y][x];
            if (cellBellowRight.getChip().getType() == chipType) {
                for (Cell lockedCell : probablyLockedCells) {
                    lockedCells.add(lockedCell);
                }
                break;
            } else if (cellBellowRight.getChip().getType() != ChipTypes.Empty) {
                probablyLockedCells.add(cellBellowRight);
            } else {
                break;
            }
        }

        return lockedCells;
    }
}
