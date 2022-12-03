package reversi.tools;

import reversi.models.Cell;
import reversi.models.GameBoard;
import reversi.models.chip.Chip;
import reversi.models.chip.ChipTypes;

import java.util.ArrayList;
import java.util.List;

import static reversi.tools.NeighboursCells.checkAroundOppositeTypeCells;

public class EmptyCells {

    public static List<Cell> getEmptyCellsOppositeType(ChipTypes chipType, Cell[][] boardCells) {
        List<Cell> emptyRightCells = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardCells[i][j].getChip().getType() == ChipTypes.Empty) {
                    if (checkAroundOppositeTypeCells(boardCells[i][j], boardCells)) {
                        emptyRightCells.add(boardCells[i][j]);
                    }
                }
            }
        }
        return emptyRightCells;
    }

}
