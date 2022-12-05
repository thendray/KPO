package thendray.tools;

import java.util.Scanner;

public class InputInformation {

    final static String whoPlayersMessage = """
            Кто будет играть?
            Нажмите 1, если хотите играть против компьютера.
            Нажмите 2, если хотите играть против другого игрока.
            """;
    final static String playerNameMessage = "\nВведите имя %s игрока: ";
    final static String gameModeChoice = """
            
            Выбирете фишку, которой будет играть первый игрок. Оппоненту достанеся вторая фишка.
            Нажмите 1, если хотите играть за круг (◯)
            Нажмите 2, если хотите играть за квадат (□)
            """;
    final static String mistakeInInputMessage = "Вы ввели %s некорректно. Попробуйте снова.\nОсталось попыток: %d\n";
    final static String forcedChoiceMessage = "Вы привыслили лимит допустимых ошибок. Автоматически будет выбрано: %s\n";
    final static String finishSessionMessage = """
    Завершить игровую сессию или продолжить играть?
    Нажмите 1, если хотите завершить
    Нажмите 2, если хотите продолжить
    """;

    final static String gameModeChoiceMessage = """
            \nВыбирете режим игры против компьютера.
            Нажмите 1, если хотите выбрать ЛЕГКИЙ режим
            Нажмите 2, если хотите выбрать СЛОЖНЫЙ режим
            """;
    final static String goBackForOneStepMessage = "\nХотите отменить ход? (введите 1, если да, 2 - нет)";

    public static String inputQuantityOfPlayers() {
        String choice;
        int mistakeCount = 5;
        Scanner in = new Scanner(System.in);
        System.out.println(whoPlayersMessage);

        while (true) {
            System.out.print("Ваш выбор: ");
            choice = in.nextLine();

            if (choice.equals("1") || choice.equals("2")) {
                break;
            } else {
                mistakeCount -= 1;
                if (mistakeCount == 0) {
                    choice = "1";
                    System.out.println(String.format(forcedChoiceMessage, "Игра с компьютером"));
                    break;
                }
                System.out.println(String.format(mistakeInInputMessage,
                        "цифру для выбора состава участников игры", mistakeCount));
            }
        }
        return choice;
    }

    public static String inputPlayerName(String playerNumber) {
        String name;
        int mistakeCount = 5;
        Scanner in = new Scanner(System.in);
        System.out.println(String.format(playerNameMessage, playerNumber));

        while (true) {
            System.out.print("Ваш выбор: ");
            name = in.nextLine();

            if (!name.isEmpty()) {
                break;
            } else {
                mistakeCount -= 1;
                if (mistakeCount == 0) {
                    name = "Mr. Bob";
                    System.out.println(String.format(forcedChoiceMessage, "имя Mr.Bob"));
                    break;
                }
                System.out.println(String.format(mistakeInInputMessage, "имя", mistakeCount));
            }
        }
        return name;
    }


    public static boolean inputChipTypeIsCircle() {
        String chipType;
        int mistakeCount = 5;
        Scanner in = new Scanner(System.in);
        System.out.println(gameModeChoice);

        while (true) {
            System.out.print("Ваш выбор: ");
            chipType = in.nextLine();

            if (chipType.equals("1") || chipType.equals("2")) {
                break;
            } else {
                mistakeCount -= 1;
                if (mistakeCount == 0) {
                    chipType = "1";
                    System.out.println(String.format(forcedChoiceMessage, "тип фишки - круг"));
                    break;
                }
                System.out.println(String.format(mistakeInInputMessage, "цифру для типа фишки", mistakeCount));
            }
        }
        return chipType.equals("1");
    }

    public static boolean inputFinishTheSession() {
        String finishSession;
        int mistakeCount = 5;
        Scanner in = new Scanner(System.in);
        System.out.println(finishSessionMessage);

        while (true) {
            System.out.print("Ваш выбор: ");
            finishSession = in.nextLine();

            if (finishSession.equals("1") || finishSession.equals("2")) {
                break;
            } else {
                mistakeCount -= 1;
                if (mistakeCount == 0) {
                    finishSession = "1";
                    System.out.println(String.format(forcedChoiceMessage, "завершить сессию"));
                    break;
                }
                System.out.println(String.format(mistakeInInputMessage, "цифру для состояния сессии", mistakeCount));
            }
        }
        return finishSession.equals("1");
    }

    public static boolean inputShouldGoBackForOneStep() {
        String shouldGoBack;
        int mistakeCount = 5;
        Scanner in = new Scanner(System.in);
        System.out.println(goBackForOneStepMessage);

        while (true) {
            System.out.print("Ваш выбор: ");
            shouldGoBack = in.nextLine();

            if (shouldGoBack.equals("1") || shouldGoBack.equals("2")) {
                break;
            } else {
                mistakeCount -= 1;
                if (mistakeCount == 0) {
                    shouldGoBack = "1";
                    System.out.println(String.format(forcedChoiceMessage, "продолжить играть"));
                    break;
                }
                System.out.println(String.format(mistakeInInputMessage, "цифру для отмены хода", mistakeCount));
            }
        }
        return shouldGoBack.equals("1");
    }

    public static String inputGameModeVersusComputer() {
        String gameModeChoice;
        int mistakeCount = 5;
        Scanner in = new Scanner(System.in);
        System.out.println(gameModeChoiceMessage);

        while (true) {
            System.out.print("Ваш выбор: ");
            gameModeChoice = in.nextLine();

            if (gameModeChoice.equals("1") || gameModeChoice.equals("2")) {
                break;
            } else {
                mistakeCount -= 1;
                if (mistakeCount == 0) {
                    gameModeChoice = "1";
                    System.out.println(String.format(forcedChoiceMessage, "ЛЕГКИЙ игровой режим"));
                    break;
                }
                System.out.println(String.format(mistakeInInputMessage, "цифру для режима игры", mistakeCount));
            }
        }
        return gameModeChoice;
    }
}
