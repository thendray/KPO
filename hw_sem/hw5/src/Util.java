import java.util.List;

public class Util {

    public static void printUsers(List<User> users) {
        System.out.println("-----------------------------------------");
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
        System.out.println("-----------------------------------------");
    }

}
