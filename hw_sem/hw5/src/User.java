public class User {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String nationality;

    public User(long id, String firstName, String lastName, int age, String nationality) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.nationality = nationality;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        User otherStudent = (User) obj;

        if (otherStudent.id != this.id) {
            return false;
        }

        if (!otherStudent.lastName.equalsIgnoreCase(this.lastName)) {
            return false;
        }

        if (!otherStudent.firstName.equalsIgnoreCase(this.firstName)) {
            return false;
        }

        if (otherStudent.age != this.age) {
            return false;
        }

        if (!otherStudent.nationality.equalsIgnoreCase(this.nationality)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 87; // #нетакойкаквсе
        long longId = Double.doubleToLongBits(id);

        result = result * 17 + (int) (longId ^ (longId >>> 32));
        result = result * 17 + firstName.toLowerCase().hashCode();
        result = result * 17 + lastName.toLowerCase().hashCode();
        result = result * 17 + age;
        result = result * 17 + nationality.toLowerCase().hashCode();

        return result;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(firstName);
        result.append(" ");
        result.append(lastName);
        result.append(", ");
        result.append(age);
        result.append(", ");
        result.append(nationality);
        result.append(", id: ");
        result.append(id);

        return result.toString();
    }
}
