package thendray.tools;

import thendray.models.Cell;
import thendray.models.GameBoard;

public class EfficiencyOfCell {

    public static double efficiencyOfCell(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();

        if ((x == 0 && y == 0) || (x == 0 && y == 7) || (x == 7 && y == 0) || (x == 7 && y == 7)) {
            return 0.8;
        }
        if (x == 0 || x == 7 || y == 0 || y == 7) {
            return 0.4;
        }
        return 0;
    }

    public static double efficiencyOfLockedCell(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();

        if (x == 0 || x == 7 || y == 0 || y == 7) {
            return 2;
        }
        return 1;
    }


}
