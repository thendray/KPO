package reversi.models;

import reversi.models.chip.Chip;
import reversi.models.chip.CircleChip;
import reversi.models.chip.EmptyChip;
import reversi.models.chip.SquareChip;

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

    public Cell(int x, int y) {
        X = x;
        Y = y;
        chip = new EmptyChip();
    }

    public Cell(int x, int y, boolean isCircleChip) {
        X = x;
        Y = y;
        if (isCircleChip) {
            chip = new CircleChip();
        } else {
            chip = new SquareChip();
        }
    }

    @Override
    public String toString() {
        return chip.toString();
    }
}
