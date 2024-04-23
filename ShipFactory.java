 public class ShipFactory {
    public static Ship createShip(ShipType type,int width, int height, int xCoordinate, int yCoordinate) {
        return switch (type) {
            case P -> new PTypeShip(width, height, xCoordinate, yCoordinate);
            case Q -> new QTypeShip(width, height, xCoordinate, yCoordinate);
        };
    }
}
