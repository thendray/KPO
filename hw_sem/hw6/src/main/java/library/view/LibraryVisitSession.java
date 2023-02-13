package library.view;

import library.controllers.ExitController;
import library.controllers.RegistrationController;
import library.models.commands.Command;
import library.models.commands.CommandType;
import library.models.user.ID;
import library.models.user.User;
import library.view.managers.LibraryManager;
import library.view.managers.RegistrationManager;

import java.io.IOException;
import java.util.List;

public class LibraryVisitSession {

    private final LibraryManager manager;
    private User currentUser;

    public LibraryVisitSession() {
        this.manager = new LibraryManager();
    }

    public void start() {
        // Вью показывает начальный экран
        String greeting = manager.greeting();

        System.out.println(greeting);

        // Направляет на регистрацию
        RegistrationManager regManager = manager.goToRegistration();

        // информация для успешной регистрации
        String registrationInfo = regManager.informVisitor();

        // Вью показывает "экран регистрации"
        System.out.println(registrationInfo);

        // информация для установления статуса(наличие чит. билета) в библиотеке
        String formForCheckingStatus = regManager.giveForm();

        while (true) {
            // Вью показывает форму
            System.out.println(formForCheckingStatus);
            System.out.print("Ответ:__");

            // получаем ответ от посетителя библиотеки
            String visitorAnswer = regManager.getStatusAnswer();

            try {
                // отправляем в контроллер данные и получаем ответ
                this.currentUser = RegistrationController.getUser(visitorAnswer, manager.getLibraryUsers());
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println("Вы допустили ошибку при заполнении формы!");
                System.out.println(exception.getMessage());
                System.out.println("Попробуйте еще раз! Вот вам новый бланк!\n");
            }

        }

        String usageInfo = manager.getInfo(currentUser.getLibraryCardIdentifier());
        System.out.println(usageInfo);

        while (true) {
            Command command = manager.getCommand();
            String commandArgs = command.getArguments();

            if (command.getCommandType() == CommandType.GET && !commandArgs.equals("")) {
                manager.getBook(commandArgs, currentUser);

            } else if (command.getCommandType() == CommandType.PUT && !commandArgs.equals("")) {
                manager.putBook(commandArgs, currentUser);

            } else if (command.getCommandType() == CommandType.LIST && commandArgs.equals("")) {
                manager.showTakenBooks(currentUser);

            } else if (command.getCommandType() == CommandType.ALL && commandArgs.equals("")) {
                manager.showAllBooksInLibrary();

            } else if (command.getCommandType() == CommandType.EXIT && commandArgs.equals("")) {
                break;
            } else {
                System.out.println("Команда нераспознана!");
            }
        }

        String byeMessage = manager.sayGoodBye(currentUser);
        System.out.println(byeMessage);

        try {
            manager.exit();
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }

        try{
            ID.saveId();
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }

    }

    private void cleanConsole() {
//        System.out.print("\033[H\033[2J");
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }

}
