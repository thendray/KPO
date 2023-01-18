import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        final int userQuantity = 30;

        List<User> users = UserGenerator.generateUsers(userQuantity);


        // 1st
        System.out.println("Список пользователей, отсортированный по возрасту!");
        var listSortByAge = users.stream()
                .sorted(
                        (user1, user2) -> Integer.compare(user1.getAge(), user2.getAge())
                ).toList();

        Util.printUsers(listSortByAge);


        // 2nd
        var mapGroupById = users.stream().collect(
                Collectors.groupingBy(User::getId)
        );
        System.out.println("Словарь пользователей по их айди!");
        System.out.println(mapGroupById);
        System.out.println("-----------------------------------------");


        // 3rd
        var countDifferentNationalities = users.stream()
                .map(User::getNationality)
                .distinct()
                .count();

        System.out.println("Количество национальностей в списке пользователей: " + countDifferentNationalities);
        System.out.println("-----------------------------------------");


        // 4th
        System.out.println("Список пользователей старше 10 лет с именем, начинающимся не на M");
        var listUsersWithRequiredFactors = users.stream()
                .filter(
                        user -> user.getAge() > 10 && user.getFirstName().charAt(0) != 'M'
                ).toList();

        Util.printUsers(listUsersWithRequiredFactors);
    }
}