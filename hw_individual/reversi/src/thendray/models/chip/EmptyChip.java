package thendray.models.chip;

public class EmptyChip extends Chip {
    public EmptyChip() {
        type = ChipTypes.Empty;
    }

    @Override
    public String toString() {
        return "   ";
    }
}
