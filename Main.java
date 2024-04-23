import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        BattleGameType gameType = BattleGameType.valueOf(sc.nextLine());
        String[] dimensions = sc.nextLine().split(" ");

        int battleAreaWidth = Integer.parseInt(dimensions[0]);
        int battleAreaHeight = dimensions[1].charAt(0) - 'A' + 1;
        int numShips = Integer.parseInt(sc.nextLine());

        BattleShipGame game = BattleShipGameFactory.initializeGame(gameType,sc);
        game.setBattleAreaWidth(battleAreaWidth);
        game.setBattleAreaHeight(battleAreaHeight);
        game.setNumShips(numShips);
        game.createPlayers();
        game.createShips();
        game.addMissileSequenceForPlayers();
        game.play();
    }
}