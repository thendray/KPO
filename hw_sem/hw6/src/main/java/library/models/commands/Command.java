package library.models.commands;

import library.models.Author;

import java.util.List;

public class Command {
    private final CommandType commandType;

    private final String arguments;

    public CommandType getCommandType() {
        return commandType;
    }

    public String getArguments() {
        return arguments;
    }

    public Command(CommandType commandType, String arguments) {
        this.commandType = commandType;
        this.arguments = arguments;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Command otherCommand = (Command) obj;

        if (!this.commandType.equals(otherCommand.commandType)) {
            return false;
        }

        if (!this.arguments.equals(otherCommand.arguments)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return commandType.hashCode() + arguments.hashCode();
    }

}
