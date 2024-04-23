import java.util.Scanner;

public class BattleShip2Player extends BattleShipGame {

    public BattleShip2Player(Scanner sc)  {
        super(sc);
    }

    @Override
    public void createPlayers() {
        players = new Player[2];
        for (int i = 0; i < 2; i++) {
            players[i] = new Player(battleAreaWidth, battleAreaHeight, numShips);
        }
    }

    @Override
    public Player getOpponentPlayer(int currentPlayerIndex) {
        return players[(currentPlayerIndex + 1) % 2];
    }

    @Override
    public int getTotalMissiles() {
        return players[0].getMissileSequence().size() +  players[1].getMissileSequence().size();
    }
}
