import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class BattleShipMultiPlayer extends BattleShipGame{

    public BattleShipMultiPlayer(Scanner sc)  {
        super(sc);
    }

    @Override
    public void createPlayers() {
        System.out.println("ENTER NO. OF PLAYERS");
        int numOfPlayers = Integer.parseInt(scanner.nextLine());
        players = new Player[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            players[i] = new Player(battleAreaWidth, battleAreaHeight, numShips);
        }
    }

    @Override
    public int getTotalMissiles() {
        return Arrays.stream(players).mapToInt(players-> players.getMissileSequence().size()).sum();
    }

    @Override
    public Player getOpponentPlayer(int currentPlayerIndex) {
        return players[Integer.parseInt(players[currentPlayerIndex].getMissileSequence().get(0).substring(2))-1];
    }
}
