package library.controllers;

import library.models.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationControllerTest {

    private static final User TEST_USER = new User("TEST", "USER");

    @Test
    @DisplayName("Проверяем, что если в ответ указан именно идентификатор и такой пользователь реально есть в списке, то возвращаем его")
    void get_user_if_user_in_list_and_card_identifier_correct_expected_user_with_this_identifier() {
        String identifier = "B";

        List<User> userListTest = new ArrayList<>();
        userListTest.add(TEST_USER);

        User userFromTesting = RegistrationController.getUser(identifier, userListTest);
        User expectedUser = TEST_USER;

        assertEquals(expectedUser, userFromTesting);
    }

    @Test
    @DisplayName("Проверяем, что если в ответ указан именно идентификатор и такой пользователя нет в списке, то кидается исключение")
    void get_user_if_user_not_in_list_and_card_identifier_correct_expected_illegal_argument_exception() {
        String identifier = "C";

        List<User> userListTest = new ArrayList<>();
        userListTest.add(TEST_USER);
        String messageFromTesting = "";
        String messageExpected = "Пользователя с таким читательским билетом не найдено!";

        try {
            User userFromTesting = RegistrationController.getUser(identifier, userListTest);
        } catch (IllegalArgumentException exception) {
            messageFromTesting = exception.getMessage();
        }

        assertEquals(messageExpected, messageFromTesting);
    }

    @Test
    @DisplayName("Проверяем, что если в ответ указаны имя и фамилия, то ожидаем нового пользователя с такими именами")
    void get_user_if_firstname_and_secondname_input_expected_new_user() {
        String identifier = "C";

        List<User> userListTest = new ArrayList<>();
        String nameAndSurnameForTesting = "tested user";
        String firstName = nameAndSurnameForTesting.split(" ")[0];
        String secondName = nameAndSurnameForTesting.split(" ")[1];


        User userFromTesting = RegistrationController.getUser(nameAndSurnameForTesting, userListTest);
        List<User> expectedList = new ArrayList<>();
        expectedList.add(userFromTesting);


        assertEquals(expectedList, userListTest);
        assertEquals(firstName, userFromTesting.getFirstName());
        assertEquals(secondName, userFromTesting.getSecondName());
        assertEquals(identifier, userFromTesting.getLibraryCardIdentifier());
    }


    @Test
    @DisplayName("Проверяем, что если в ответ указано что-то длиной ни один, ни два (то есть ни идентификатор, ни фамилия-имя), то кидается исключение")
    void get_user_if_input_format_incorrect_expected_illegal_argument_exception() {
        String input = "SOME Thing weird";

        List<User> userListTest = new ArrayList<>();
        String messageFromTesting = "";
        String messageExpected = "Форма заполнена не верно! Проверьте, что ответ записан согласно правилам!";

        try {
            User userFromTesting = RegistrationController.getUser(input, userListTest);
        } catch (IllegalArgumentException exception) {
            messageFromTesting = exception.getMessage();
        }

        assertEquals(messageExpected, messageFromTesting);
    }


}