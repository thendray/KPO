package thendray.models;

import thendray.models.chip.ChipTypes;

import java.util.List;

import static thendray.tools.LockedCells.getLockedCells;

public class GameBoard {
    private final Cell[][] boardCells;

    public Cell[][] getBoardCells() {
        return boardCells;
    }
    public GameBoard(Cell[][] boardCells) {
        this.boardCells = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.boardCells[i][j] = boardCells[i][j];
            }
        }
    }


    public GameBoard() {
        boardCells = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == i && (i == 3 || i == 4)) {
                    boardCells[i][j] = new Cell(i, j, true);
                } else if ((i == 4 && j == 3) || (i == 3 && j == 4)) {
                    boardCells[i][j] = new Cell(i, j, false);
                } else {
                    boardCells[i][j] = new Cell(i, j);
                }
            }
        }
    }


    public boolean isOneTypeOfChipOnBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = j + 1; k < 8; k++) {
                    if (boardCells[i][k].getChip().getType() != boardCells[i][j].getChip().getType()
                            && boardCells[i][k].getChip().getType() != ChipTypes.Empty) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isNotFull() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardCells[i][j].chip.getType() == ChipTypes.Empty) {
                    return true;
                }
            }
        }

        return false;
    }

    public void putChipOnCell(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();
        List<Cell> lockedCells = getLockedCells(cell, cell.getChip().getType(), boardCells);

        boardCells[y][x] = cell;
        for (Cell lockedCell : lockedCells) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (boardCells[i][j].equals(lockedCell)) {
                        boardCells[i][j] = new Cell(i, j, lockedCell.chip.getType() != ChipTypes.Circle);
                    }
                }
            }
        }
    }

    public String gameBoardWithPrompts(List<Cell> promptCells) {
        StringBuilder result = new StringBuilder();
        int numberOfRow = 'A';
        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                result.append("   _______________________________________________\n");
            } else {
                result.append("  |-----+-----+-----+-----+-----+-----+-----+-----|\n");
            }
            result.append(Character.toString(numberOfRow)).append(" | ");
            numberOfRow += 1;
            for (int j = 0; j < 8; j++) {
                boolean isPromptCell = false;
                for (Cell prompt : promptCells) {
                    if (boardCells[i][j].equals(prompt)) {
                        result.append(" ◆  | ");
                        isPromptCell = true;
                        break;
                    }
                }
                if (!isPromptCell){
                    result.append(boardCells[i][j]).append(" | ");
                }
            }
            result.append("\n");
        }
        result.append("   ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n");
        result.append("     1     2     3     4     5     6     7     8");
        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int numberOfRow = 'A';
        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                result.append("   _______________________________________________\n");
            } else {
                result.append("  |-----+-----+-----+-----+-----+-----+-----+-----|\n");
            }
            result.append(Character.toString(numberOfRow)).append(" | ");
            numberOfRow += 1;
            for (int j = 0; j < 8; j++) {
                result.append(boardCells[i][j]).append(" | ");
            }
            result.append("\n");
        }
        result.append("   ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n");
        result.append("     1     2     3     4     5     6     7     8");
        return result.toString();
    }
}
