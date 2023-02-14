package library.tools.forconsole;

import library.models.book.Book;

import java.util.List;
import java.util.Scanner;

import static library.tools.BookListInText.getBookListInText;

public class ChoiceMaker {

    /**
     * Choosing book from t=list of books with same name
     * @param booksWithSameName - list with books
     * @return book which has been chosen
     */
    public static Book choseBookSecondTry(List<Book> booksWithSameName) {
        String bookVariants = getBookListInText(booksWithSameName);
        System.out.println(bookVariants);

        while (true) {
            System.out.print("Укажите номер из списка, соответсвующий желаемой книге:__");
            Scanner in = new Scanner(System.in);
            int answer = 0;

            try {
                answer = Integer.parseInt(in.nextLine());

                if (answer > 0 && answer <= bookVariants.length()) {
                    return booksWithSameName.get(answer - 1);
                } else {
                    throw new NumberFormatException();
                }

            } catch (NumberFormatException e) {
                System.out.println("Такого номера в списке нет! Попробуйте снова.\n");
            }
        }
    }



}
