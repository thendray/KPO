package reversi.models.player;

import reversi.models.Cell;

public abstract class Player {
    String name;
    boolean isComputer;
    boolean isCircleChip;

    public String getName() {
        return name;
    }

    public boolean isComputer() {
        return isComputer;
    }

    public boolean isCircleChip() {
        return isCircleChip;
    }

    public abstract Cell choseCell(Cell[][] gameBoard);
}


