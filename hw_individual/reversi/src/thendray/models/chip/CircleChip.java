package thendray.models.chip;

public class CircleChip extends Chip {
    public CircleChip() {
        type = ChipTypes.Circle;
    }

    @Override
    public String toString() {
        return " ◯ ";
    }
}
