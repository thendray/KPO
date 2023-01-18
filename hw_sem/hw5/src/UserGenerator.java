import java.util.ArrayList;
import java.util.List;

public class UserGenerator {

    public static List<User> generateUsers(int quantity) {
        List<User> users = new ArrayList<>(quantity);

        final int maxAge = 70;
        final int minAge = 1;
        int nameDataSize = FirstNameFemaleData.values().length;
        int nationalityDataSize = NationalityData.values().length;

        int randomNumForLastName;
        int randomNumForFirstName;
        int randomNumForNationality;
        long id;
        String firstName;
        String lastName;
        String nationality;
        int age;

        for (int i = 0; i < quantity; i++) {
            randomNumForFirstName = (int) (Math.random() * nameDataSize);
            randomNumForLastName = (int) (Math.random() * nameDataSize);
            randomNumForNationality = (int) (Math.random() * nationalityDataSize);
            id =  10000L + i;

            if (i % 2 == 0) {
                firstName = FirstNameMaleData.values()[randomNumForFirstName].toString();
                lastName = LastNameMaleData.values()[randomNumForLastName].toString();
            } else {
                firstName = FirstNameFemaleData.values()[randomNumForFirstName].toString();
                lastName = LastNameFemaleData.values()[randomNumForLastName].toString();
            }

            nationality = NationalityData.values()[randomNumForNationality].toString();
            age = minAge + (int) (Math.random() * (maxAge - minAge));

            users.add(new User(id, firstName, lastName, age, nationality));
        }

        return users;
    }

}
