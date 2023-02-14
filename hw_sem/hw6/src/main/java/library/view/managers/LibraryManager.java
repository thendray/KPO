package library.view.managers;

import library.controllers.ExitController;
import library.controllers.GetBookController;
import library.controllers.PutBookController;
import library.controllers.UserDataController;
import library.models.book.Taken;
import library.models.commands.Command;
import library.models.commands.CommandType;
import library.models.Library;
import library.models.book.Book;
import library.models.user.User;
import library.tools.BookListInText;

import java.util.List;
import java.util.Scanner;

import static library.tools.forconsole.ChoiceMaker.choseBookSecondTry;

public class LibraryManager {

    private final Library library;

    public List<User> getLibraryUsers() {
        return libraryUsers;
    }

    private final List<User> libraryUsers;

    public LibraryManager() {
        this.library = new Library();
        libraryUsers = UserDataController.getUsersFromData();
        library.generateBooks();
    }

    public String greeting() {
        return """
                Приветствую вас в нашей библиотеке!
                По всем вопросам можете обращаться ко мне, вашему персональному менеджеру!
                    
                Для начала пройдите верификацию, мы проверим есть ли ваши данные в базе пользователей.
                Если нет, то вам необходимо будет получить читательский билет.
                """;
    }

    public RegistrationManager goToRegistration() {
        return new RegistrationManager();
    }

    public Command getCommand() {
        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            String[] arguments = line.split("\\s+");
            StringBuilder commandArgument = new StringBuilder();

            String command = arguments[0];

            for (int i = 1; i < arguments.length; ++i) {
                commandArgument.append(arguments[i]);
                commandArgument.append(" ");
            }

            if (commandArgument.length() > 0) {
                commandArgument.delete(commandArgument.length() - 1, commandArgument.length());
            }

           return choseCommand(command, commandArgument.toString());
        }
    }

    public Command choseCommand(String command, String commandArgument) {
        if (command.equals("/put") && !commandArgument.equals("")) {
            return new Command(CommandType.PUT, commandArgument);

        } else if (command.equals("/exit") && commandArgument.equals("")) {
            return new Command(CommandType.EXIT, commandArgument);

        } else if (command.equals("/get") && !commandArgument.equals("")) {
            return new Command(CommandType.GET, commandArgument);

        } else if (command.equals("/all") && commandArgument.equals("")) {
            return new Command(CommandType.ALL, commandArgument);

        } else if (command.equals("/list") && commandArgument.equals("")) {
            return new Command(CommandType.LIST, commandArgument);

        } else {
            return new Command(CommandType.UNRECOGNIZED, commandArgument);
        }
    }

    private static final String INFO_TEXT = """
            Регистрация пройдена! Ваш идентификатор читательского билета: %s
            Ознакомьтесь с доступными вам опциями:
                            
            1. /get <book_name> - взять книгу с названием <book_name>
            2. /put <book_name> - вернуть книгу <book_name> обратно
            3. /list - посмотреть список взятых книг
            4. /all - посмотреть все книги, которые есть в библиотеке
            5. /exit - завершить сеанс и выйти из библиотеки
                            
            Далее просто вводите необходимую вам команду в консоль! Хорошего дня!\n
            """;

    public String getInfo(String identifier) {
        return String.format(INFO_TEXT, identifier);
    }

    public String sayGoodBye(User user) {
        return String.format("До свидания, %s %s. Ждем вас снова!", user.getFirstName(), user.getSecondName());
    }

    public void getBook(String bookName, User user) {
        List<Book> booksWithSameName = GetBookController.getBooksByName(bookName, library);
        Book gettingBook;

        if (booksWithSameName.size() == 0) {
            System.out.printf("Книги с названием '%s' нет в библиотеке!\n\n", bookName);
            return;

        } else if (booksWithSameName.size() == 1) {
            gettingBook = booksWithSameName.get(0);

        } else {
            gettingBook = choseBookSecondTry(booksWithSameName);
        }

        if (!Taken.class.isAssignableFrom(gettingBook.getClass())) {
            System.out.println("Данную книгу нельзя взять! Это достояние библиотеки!");

        } else {
            user.takeBook(gettingBook);
            library.someoneTakesBook(gettingBook);
        }
    }


    public void putBook(String bookName, User currentUser) {

        List<Book> matchingBooksForPuttingBack = PutBookController.returnBookByName(bookName, currentUser.getTakenBooks());
        Book puttingBook;

        if (matchingBooksForPuttingBack.size() == 0) {
            System.out.printf("Вы не брали книгу с названием '%s'!\n\n", bookName);
            return;
        }

        if (matchingBooksForPuttingBack.size() == 1) {
            puttingBook = matchingBooksForPuttingBack.get(0);
        } else {
            puttingBook = choseBookSecondTry(matchingBooksForPuttingBack);
        }

        currentUser.putBack(puttingBook);
        library.addBookToCollection(puttingBook);
    }


    private static final String BOOK_LIST = """
            Пользователь: %s %s. Читательский билет: %s
            Взятые книги: 
            %s
            """;

    public void showTakenBooks(User currentUser) {
        String booksListInText = BookListInText.getBookListInText(currentUser.getTakenBooks());


        System.out.printf(BOOK_LIST,
                currentUser.getFirstName(),
                currentUser.getSecondName(),
                currentUser.getLibraryCardIdentifier(),
                booksListInText);

        if (booksListInText.equals("")) {
            System.out.println("Пусто...\n");
        }
    }


    public void showAllBooksInLibrary() {
        String libraryListOfBooksInText = BookListInText.getBookListInText(library.getBookCollection());

        if (libraryListOfBooksInText.equals("")) {
            System.out.println("В библиотеке нет книг!\n");
        } else {
            System.out.println(libraryListOfBooksInText);
        }
    }

    public void exit() {
        ExitController.exit(libraryUsers);
    }
}
