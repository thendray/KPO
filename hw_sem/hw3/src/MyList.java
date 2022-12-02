public class MyList implements IntegerList {
    private int size;
    private int capacity;
    private Integer[] myList;


    public MyList() {
        size = 0;
        capacity = 4;
        myList = new Integer[capacity];
    }

    public MyList(int size) {
        this.size = size;
        capacity = 2 * size;
        myList = new Integer[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        if (Integer.MAX_VALUE - capacity > capacity){
            capacity *= 2;
        } else {
            capacity = Integer.MAX_VALUE;
        }
        Integer[] newMyList = new Integer[capacity];
        for (int i = 0; i < size; i++) {
            newMyList[i] = myList[i];
        }
        myList = newMyList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == myList[i]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            return myList[index];
        }
    }

    @Override
    public void add(int index, Integer element) {
        if (index > size){
            throw new IndexOutOfBoundsException();
        }

        if (size == Integer.MAX_VALUE) {
            return;
        }

        if (size == capacity) {
            resize();
        }

        for (int i = size - 1; i >= index; i--) {
            myList[i + 1] = myList[i];
        }
        myList[index] = element;
        size += 1;
    }

    @Override
    public Integer remove(int index) {
        if (index > size){
            throw new IndexOutOfBoundsException();
        }
        Integer number = myList[index];

        for (int i = index + 1; i < size; i++) {
            myList[i - 1] = myList[i];
        }
        size -= 1;
        return number;
    }

    @Override
    public int indexOf(Integer o) {
        for (int i = 0; i < size; i++) {
            if (o == myList[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean add(Integer e) {
        add(size, e);
        return true;
    }

    @Override
    public boolean remove(Integer o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder myListString = new StringBuilder();
        myListString.append("[");

        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                myListString.append(String.format("%d", myList[i]));

            }else {
                myListString.append(String.format("%d, ", myList[i]));
            }
        }

        myListString.append("]");
        return myListString.toString();
    }

    public void print() {
        System.out.println(this);
    }
}
