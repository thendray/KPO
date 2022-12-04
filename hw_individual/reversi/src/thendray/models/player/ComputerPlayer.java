package thendray.models.player;

import thendray.models.Cell;
import thendray.models.chip.ChipTypes;

public class ComputerPlayer extends Player {
    public ComputerPlayer(ChipTypes chipType) {
        this.name = "R2-D2";
        this.chipType = chipType;
        isComputer = true;
    }

    @Override
    public Cell choseCell(Cell[][] gameBoard) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard[i][j].getChip().getType() == ChipTypes.Empty) {
                    return new Cell(i, j, chipType == ChipTypes.Circle);
                }
            }
        }
        return new Cell(0, 0);
    }
}
