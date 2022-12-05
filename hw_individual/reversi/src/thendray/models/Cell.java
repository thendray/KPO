package thendray.models;

import thendray.models.chip.Chip;
import thendray.models.chip.CircleChip;
import thendray.models.chip.EmptyChip;
import thendray.models.chip.SquareChip;

public class Cell {
    private final int X;
    private final int Y;
    Chip chip;

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public Chip getChip() {
        return chip;
    }

    public Cell(int y, int x) {
        X = x;
        Y = y;
        chip = new EmptyChip();
    }

    public Cell(int y, int x, boolean isCircleChip) {
        X = x;
        Y = y;
        if (isCircleChip) {
            chip = new CircleChip();
        } else {
            chip = new SquareChip();
        }
    }

    public boolean equals(Cell cell) {
        if (cell.X == X && cell.Y == Y) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return chip.toString();
    }
}
