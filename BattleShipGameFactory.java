import java.util.Scanner;

public class BattleShipGameFactory {
    public static BattleShipGame initializeGame(BattleGameType type, Scanner sc) {
        switch(type) {
            case DOUBLE_PLAYER -> {
                return new BattleShip2Player(sc);
            }
            case MULTI_PLAYER -> {
                return new BattleShipMultiPlayer(sc);
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
