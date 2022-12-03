package reversi.models.chip;

public class SquareChip extends Chip {
    public SquareChip() {
        type = ChipTypes.Square;
    }

    @Override
    public String toString() {
        return " □";
    }
}
