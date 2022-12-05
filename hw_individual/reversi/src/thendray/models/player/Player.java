package thendray.models.player;

import thendray.models.Cell;
import thendray.models.chip.ChipTypes;

import java.util.List;

public abstract class Player {
    String name;
    boolean isComputer;
    ChipTypes chipType;
    public String getName() {
        return name;
    }

    public ChipTypes getChipType() {
        return chipType;
    }

    public boolean isComputer() {
        return isComputer;
    }

    public abstract Cell choseCell(Cell[][] gameBoard);


    public abstract List<Cell> getAvailableCellsForMove(Cell[][] boardCells);
}


