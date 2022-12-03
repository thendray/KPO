package reversi.gameControl;

import static reversi.tools.InputInformation.inputFinishTheSession;

public class GameSession {
    Game currentGame;
    int bestGamePoints;
    final String startGreeting = "Приветствую тебя в игре РЕВЕРСИ";
    final String gameResultsMessage = "Игра завершена со слежующими результатами: ";
    final String endingGameMessage = "\nСпасибо за игру! Может сыграем еще?!\n";
    final String finishSessionMessage = "\nЗдорово провели время! Лучший результат за все время - . До новых встреч!";
    final String newGameMessage = "\nДавай сыграем!!!\n";


    public GameSession() {
        System.out.println(startGreeting);
    }

    public void startNewGame() {
        System.out.println(newGameMessage);

        currentGame = new Game();
        currentGame.startGame();

        System.out.println(gameResultsMessage);
        int points = currentGame.gameResults();

        if (points > bestGamePoints) {
            bestGamePoints = points;
        }

        System.out.println(endingGameMessage);
    }

    public boolean mayFinishSession() {
        boolean finishSession = inputFinishTheSession();
        if (finishSession) {
            System.out.println(finishSessionMessage);
        }
        return finishSession;
    }

}
