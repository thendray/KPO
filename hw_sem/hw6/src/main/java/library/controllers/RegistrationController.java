package library.controllers;

import library.models.user.User;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class RegistrationController {

    public static User getUser(String userAnswerStatus, List<User> users) {
        String[] answerParts = userAnswerStatus.split(" ");
        int length = answerParts.length;

        User user;

        switch (length) {
            case 1:
                user = getUserByCardIdentifier(answerParts[0], users);
                if (user == null) {
                    throw new IllegalArgumentException("Пользователя с таким читательским билетом не найдено!");
                }
                break;

            case 2:
                user = new User(answerParts[0], answerParts[1]);
                users.add(user);
                break;

            default:
                throw new IllegalArgumentException("Форма заполнена не верно! Проверьте, что ответ записан согласно правилам!");
        }

        return user;

    }

    private static User getUserByCardIdentifier(String answerPart, List<User> libraryUsers) {

        for (User user : libraryUsers) {
            if (user.getLibraryCardIdentifier().equals(answerPart)) {
                return user;
            }
        }
        return null;
    }


}
