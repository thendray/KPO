package library.view.managers;

import library.models.commands.Command;
import library.models.commands.CommandType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryManagerTest {

    private static final LibraryManager MANAGER = new LibraryManager();


    @Test
    @DisplayName("Проверяем, что если введена команда не требующая аргументов, без аргументов, то ожидаем эту команду")
    void chose_command_with_no_arguments_input_correct_expect_right_command() {
        String inputLine = "/list";

        Command testingCommand = MANAGER.choseCommand(inputLine, "");

        Command expectedCommand = new Command(CommandType.LIST, "");

        assertEquals(expectedCommand, testingCommand);

    }

    @Test
    @DisplayName("Проверяем, что если введена команда не требующая аргументов, c аргументами, то ожидаем команду - нераспознано")
    void chose_command_with_no_arguments_input_incorrect_expect_unrecognized_command() {
        String inputLine = "/list";
        String args = "some arguments";
        Command testingCommand = MANAGER.choseCommand(inputLine, args);

        Command expectedCommand = new Command(CommandType.UNRECOGNIZED, args);

        assertEquals(expectedCommand, testingCommand);

    }


    @Test
    @DisplayName("Проверяем, что если введена команда требующая аргументов, без аргументов, то ожидаем нераспознанную команду")
    void chose_command_with_arguments_input_incorrect_expect_unrecognized_command() {
        String inputLine = "/put";
        String args = "";

        Command testingCommand = MANAGER.choseCommand(inputLine, args);

        Command expectedCommand = new Command(CommandType.UNRECOGNIZED, args);

        assertEquals(expectedCommand, testingCommand);
    }

    @Test
    @DisplayName("Проверяем, что если введена команда требующая аргументов, c аргументами, то ожидаем эту команду")
    void chose_command_with_arguments_input_correct_expect_right_command() {
        String inputLine = "/put";
        String args = "Book about testing";

        Command testingCommand = MANAGER.choseCommand(inputLine, args);

        Command expectedCommand = new Command(CommandType.PUT, args);

        assertEquals(expectedCommand, testingCommand);
    }

}