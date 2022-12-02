public class Main {
    public static void main(String[] args) {
        MyList myList = new MyList();
        Integer num1 = 5;

        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.print();

        myList.add(4);
        myList.add(1, 5);
        myList.print();

        myList.remove(0);
        System.out.println(myList.indexOf(num1));
        myList.remove(num1);
        myList.print();

        myList.remove(myList.get(2));
        myList.print();

        System.out.println(myList.contains(num1));
    }
}