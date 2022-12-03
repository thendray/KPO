package reversi.models;

import reversi.models.chip.ChipTypes;

public class GameBoard {
    private final Cell[][] boardCells;

    public Cell[][] getBoardCells() {
        return boardCells;
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

    public void changeCell(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();

        boardCells[x][y] = cell;
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
