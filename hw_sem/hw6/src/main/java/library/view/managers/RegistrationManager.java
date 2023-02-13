package library.view.managers;

import library.models.book.Book;

import java.util.Scanner;

public class RegistrationManager {
    public String informVisitor() {
        return """
                Вы у стойки регистрации!
                В предложенной ниже форме, выберете соответсвующий вам вариант!
                
                Если у вас есть читательский билет, то в ответ запишите его идентификатор!
                Если нет, то в ответ запишите ваше имя и Фамилию через пробел!
                
                """;
    }

    public String giveForm() {
        return """
                 _______________________________________________________________________
                | 1. У меня есть читательский билет! |В ответ я укажу его идентификатор |
                | ---------------------------------- |--------------------------------- |
                | 2. У меня нет читательского билета!|В ответ я укажу свои имя и фамилию|
                | ---------------------------------- |--------------------------------- |
                """;
    }

    public String getStatusAnswer() {
        Scanner in = new Scanner(System.in);

        return in.nextLine();
    }
}