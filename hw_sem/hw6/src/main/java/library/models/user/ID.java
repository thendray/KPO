package library.models.user;


import java.io.*;

public class ID implements Serializable {
    private static int userId = getIdFromData();

    private static int getIdFromData() {
        int currentID = 1;
        String dataFile = "src" + File.separator + "main" + File.separator + "java"
                + File.separator + "library" + File.separator + "data" + File.separator + "id.json";

        try {
            FileInputStream fileInputStream = new FileInputStream(dataFile);
            int idData = fileInputStream.read();
            fileInputStream.close();

            if (idData != -1) {

                try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
                    currentID = (int)ois.readObject();
                }
                catch(Exception ex) {
                    throw new RuntimeException("Возникли проблемы при чтении данных!");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }

        return currentID;
    }

    public static void saveId() {
        String dataFile = "src" + File.separator + "main" + File.separator + "java"
                + File.separator + "library" + File.separator + "data" + File.separator + "id.json";

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile)))
        {
            oos.writeObject(userId);
        }
        catch(Exception ex){
            throw new RuntimeException("Возникли проблемы при записи данных!");
        }
    }

    public static int getUserId() {
        return userId++;
    }

}
