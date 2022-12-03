package reversi.models.player;

import reversi.models.Cell;
import reversi.models.chip.ChipTypes;

public class ComputerPlayer extends Player {
    public ComputerPlayer(boolean isCircleChip) {
        this.name = "R2-D2";
        this.isCircleChip = isCircleChip;
        isComputer = true;
    }

    @Override
    public Cell choseCell(Cell[][] gameBoard) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard[i][j].getChip().getType() == ChipTypes.Empty) {
                    return new Cell(i, j, isCircleChip);
                }
            }
        }
        return new Cell(0, 0);
    }
}
