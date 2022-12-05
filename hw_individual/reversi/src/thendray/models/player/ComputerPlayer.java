package thendray.models.player;

import thendray.gameControl.Game;
import thendray.models.Cell;
import thendray.models.GameBoard;
import thendray.models.chip.ChipTypes;

import java.util.ArrayList;
import java.util.List;

import static thendray.tools.CellsForNextMove.getRightCells;
import static thendray.tools.EfficiencyOfCell.efficiencyOfCell;
import static thendray.tools.EfficiencyOfCell.efficiencyOfLockedCell;
import static thendray.tools.LockedCells.getLockedCells;

public class ComputerPlayer extends Player {

    boolean isLightMode;

    public void setLightMode(boolean lightMode) {
        isLightMode = lightMode;
    }

    public ComputerPlayer(ChipTypes chipType, boolean isLightMode) {
        this.name = "R2-D2";
        this.chipType = chipType;
        isComputer = true;
        this.isLightMode = isLightMode;
    }

    @Override
    public Cell choseCell(Cell[][] boardCells) {
        Cell chosenCell;
        if (isLightMode) {
            chosenCell = choseCellLightMode(boardCells);
        } else {
            chosenCell = choseCellHardMode(boardCells);
        }
        return chosenCell;
    }

    private double countEfficiencyOfLockedCells(List<Cell> lockedCells, Cell cell) {
        double cellValue = 0;

        cellValue += efficiencyOfCell(cell);

        for (Cell lockedCell : lockedCells) {
            cellValue += efficiencyOfLockedCell(lockedCell);
        }

        return cellValue;
    }

    private Cell choseCellHardMode(Cell[][] boardCells) {
        List<Cell> probablyCellsForMove = getAvailableCellsForMove(boardCells);
        List<Cell> probablyCellsForNextMove;
        if (probablyCellsForMove.isEmpty()) {
            return null;
        }

        List<Double> countOfEfficiencyOfAvailableCells = new ArrayList<>();
        List<Double> countOfEfficiencyOfCellsNextMove = new ArrayList<>();
        List<Cell> lockedCells;
        GameBoard helperGameBoard;
        double cellValue = 0;

        for (Cell cell : probablyCellsForMove) {

            lockedCells = getLockedCells(cell, chipType, boardCells);
            cellValue = countEfficiencyOfLockedCells(lockedCells, cell);
            countOfEfficiencyOfAvailableCells.add(cellValue);

        }

        ChipTypes oppositeChipType = ChipTypes.values()[(chipType.ordinal() + 1) % 2];

        for (Cell cell : probablyCellsForMove) {
            helperGameBoard = new GameBoard(boardCells);
            helperGameBoard.putChipOnCell(cell);


            probablyCellsForNextMove = getRightCells(oppositeChipType, helperGameBoard.getBoardCells());

            if (probablyCellsForNextMove.isEmpty()) {
                countOfEfficiencyOfCellsNextMove.add(0d);
                continue;
            }

            double maxValue = 0;

            for (Cell nextMoveCell : probablyCellsForNextMove) {
                lockedCells = getLockedCells(nextMoveCell, oppositeChipType, helperGameBoard.getBoardCells());

                cellValue = countEfficiencyOfLockedCells(lockedCells, nextMoveCell);

                if (cellValue > maxValue) {
                    maxValue = cellValue;
                }
            }

            countOfEfficiencyOfCellsNextMove.add(maxValue);
        }

        double maxResult = -100;
        int maxResultIndex = 0;
        double result;
        double currentValue;
        double nextMoveValue;

        for (int i = 0; i < probablyCellsForMove.size(); i++) {
            currentValue = countOfEfficiencyOfAvailableCells.get(i);
            nextMoveValue = countOfEfficiencyOfCellsNextMove.get(i);
            result = currentValue - nextMoveValue;

            if (result > maxResult) {
                maxResult = result;
                maxResultIndex = i;
            }
        }
        Cell bestCell = probablyCellsForMove.get(maxResultIndex);
        Cell newBestCell = new Cell(bestCell.getY(), bestCell.getX(), chipType == ChipTypes.Circle);
        return newBestCell;
    }

    private Cell choseCellLightMode(Cell[][] boardCells) {
        List<Cell> probablyCellsForMove = getAvailableCellsForMove(boardCells);
        if (probablyCellsForMove.isEmpty()) {
            return null;
        }
        double maxValue = 0;
        int maxValueIndex = 0;
        int count = 0;

        List<Cell> lockedCells;
        double cellValue = 0;

        for (Cell cell : probablyCellsForMove) {
            lockedCells = getLockedCells(cell, chipType, boardCells);

            cellValue = countEfficiencyOfLockedCells(lockedCells, cell);

            if (cellValue > maxValue) {
                maxValue = cellValue;
                maxValueIndex = count;
            }
            count += 1;
        }
        Cell bestCell = probablyCellsForMove.get(maxValueIndex);
        Cell newBestCell = new Cell(bestCell.getY(), bestCell.getX(), chipType == ChipTypes.Circle);
        return newBestCell;
    }


    @Override
    public List<Cell> getAvailableCellsForMove(Cell[][] boardCells) {
        return getRightCells(chipType, boardCells);
    }
}
