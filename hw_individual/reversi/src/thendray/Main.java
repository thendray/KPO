package thendray;

import thendray.gameControl.GameSession;

public class Main {
    public static void main(String[] args) {
        GameSession session = new GameSession();
        while (true) {
            session.startNewGame();

            if (session.mayFinishSession()) {
                break;
            }
        }
    }
}