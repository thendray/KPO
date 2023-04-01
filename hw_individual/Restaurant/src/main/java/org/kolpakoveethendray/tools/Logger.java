package org.kolpakoveethendray.tools;

import org.kolpakoveethendray.threads.Chef;
import org.kolpakoveethendray.models.Order;
import org.kolpakoveethendray.config.RestaurantData;
import org.kolpakoveethendray.threads.Visitor;
import org.kolpakoveethendray.models.Time;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;


public class Logger {
    private static File file;
    private static boolean append = false;

    public static void setFile(File file) {
        Logger.file = file;
    }

    private static final String EXCEPTION_MESSAGE =
            """
            Возникли проблемы при записи информации о %s!
            Проверьте корректность файла и попробуйте снова! (Ниже вы можете ознакомиться с ошибкой)
            %s
            
            """;

    public static void visitorEnterLog(Visitor visitor) {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            String text = String.format("%s - Visitor#%s enters the restaurant\n", Time.getTimeStamp(), visitor.getName());
            fileWriter.write(text);
            append = true;
        } catch (IOException ex) {
            System.out.printf(EXCEPTION_MESSAGE, "приходе посетителя", ex.getMessage());
            throw new UncheckedIOException(ex); // делать так
            //throw new RuntimeException(ex); // никогда так не делать
        }
    }

    public static void visitorSitDownLog(Visitor visitor, int tableNumber) {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            String text = String.format("%s - Visitor#%s sits down at the table №%d\n", Time.getTimeStamp(), visitor.getName(), tableNumber);
            fileWriter.write(text);
            append = true;
        } catch (IOException ex) {
            System.out.printf(EXCEPTION_MESSAGE, "займе свободного места", ex.getMessage());
            throw new RuntimeException();

        }
    }

    public static void visitorLookMenuLog(Visitor visitor) {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            String text = String.format("%s - Visitor#%s is looking at the menu\n", Time.getTimeStamp(), visitor.getName());
            fileWriter.write(text);
            append = true;
        } catch (IOException ex) {
            System.out.printf(EXCEPTION_MESSAGE, "просмотре посетителем меню", ex.getMessage());
            throw new RuntimeException();
        }
    }

    public static void visitorMakeOrderLog(Visitor visitor, Order order) {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            String text = String.format("%s - Visitor#%s makes the order №%d: %s\n",
                    Time.getTimeStamp(), visitor.getName(), order.getOrderNumber(), order);
            fileWriter.write(text);
            append = true;
        } catch (IOException ex) {
            System.out.printf(EXCEPTION_MESSAGE, "оформлении посетителем заказа", ex.getMessage());
            throw new RuntimeException();
        }
    }

    public static void visitorLeaveLog(Visitor visitor) {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            String text = String.format("%s - Visitor#%s leaves the restaurant\n",
                    Time.getTimeStamp(), visitor.getName());
            fileWriter.write(text);
            append = true;
        } catch (IOException ex) {
            System.out.printf(EXCEPTION_MESSAGE, "уходе посетителя", ex.getMessage());
        }
    }

    public static void visitorPayLog(Visitor visitor, Order order) {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            String text = String.format("%s - Visitor#%s pays %d rubles for the order №%d\n",
                    Time.getTimeStamp(), visitor.getName(), order.getPrice(), order.getOrderNumber());
            fileWriter.write(text);
            append = true;
        } catch (IOException ex) {
            System.out.printf(EXCEPTION_MESSAGE, "расплате посетителя", ex.getMessage());
        }
    }

    public static void managerLeaveLog() {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            String text = String.format("%s - Manager leaves the restaurant\n", Time.getTimeStamp());
            fileWriter.write(text);
            append = true;
        } catch (IOException ex) {
            System.out.printf(EXCEPTION_MESSAGE, "уходе менеджера", ex.getMessage());
            throw new RuntimeException();
        }
    }

    public static void managerTakeOrderLog(Order order, long time) {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            String text = String.format("%s - Manager takes the order №%d: %s. Estimated waiting time: %d min\n",
                    Time.getTimeStamp(), order.getOrderNumber(), order, time);
            fileWriter.write(text);
            append = true;
        } catch (IOException ex) {
            System.out.printf(EXCEPTION_MESSAGE, "приеме заказа", ex.getMessage());
            throw new RuntimeException();
        }
    }

    public static void managerRejectOrderLog(Order order) {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            String text = String.format("%s - Manager rejects the order №%d: %s, because the waiting time for the order is too long\n",
                    Time.getTimeStamp(), order.getOrderNumber(), order);
            fileWriter.write(text);
            append = true;
        } catch (IOException ex) {
            System.out.printf(EXCEPTION_MESSAGE, "отмене заказа", ex.getMessage());
            throw new RuntimeException();
        }
    }

    public static void chefCookOrderLog(Chef chef, Order order) {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            String text = String.format("%s - %s cooks the order №%d: %s\n",
                    Time.getTimeStamp(), chef.getName(), order.getOrderNumber(), order);
            fileWriter.write(text);
            append = true;
        } catch (IOException ex) {
            System.out.printf(EXCEPTION_MESSAGE, "начале готовки заказа", ex.getMessage());
            throw new RuntimeException();
        }
    }

    public static void chefFinishCookLog(Chef chef, Order order) {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            String text = String.format("%s - %s finishes cooking the order №%d: %s\n",
                    Time.getTimeStamp(), chef.getName(), order.getOrderNumber(), order);
            fileWriter.write(text);
            append = true;
        } catch (IOException ex) {
            System.out.printf(EXCEPTION_MESSAGE, "окончании приготовления заказа", ex.getMessage());
            throw new RuntimeException();
        }
    }

    public static void chefLeaveLog(Chef chef) {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            String text = String.format("%s - %s leaves the restaurant\n", Time.getTimeStamp(), chef.getName());
            fileWriter.write(text);
            append = true;
        } catch (IOException ex) {
            System.out.printf(EXCEPTION_MESSAGE, "уходе шефа", ex.getMessage());
            throw new RuntimeException();
        }
    }

    public static void restaurantOpenLog() {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            String text = String.format("%s - Restaurant opens. Working hours: %d\n",
                    Time.getTimeStamp(), RestaurantData.getWorkHours());
            fileWriter.write(text);
            append = true;
        } catch (IOException ex) {
            System.out.printf(EXCEPTION_MESSAGE, "открытии ресторана", ex.getMessage());
            throw new RuntimeException();
        }
    }

    public static void restaurantCloseLog() {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            String text = String.format("%s - Restaurant closes\n", Time.getTimeStamp());
            fileWriter.write(text);
            append = true;
        } catch (IOException ex) {
            System.out.printf(EXCEPTION_MESSAGE, "закрытие ресторана", ex.getMessage());
            throw new RuntimeException();
        }
    }

    public static void restaurantProfitLog(int profit) {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            String text = String.format("Total profit: %d rubles\n", profit);
            fileWriter.write(text);
            append = true;
        } catch (IOException ex) {
            System.out.printf(EXCEPTION_MESSAGE, "прибыли ресторана", ex.getMessage());
        }
    }
}
