package library.models.commands;

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


}
