public class PTypeShip extends Ship {

    public PTypeShip(int width, int height, int xCoordinate, int yCoordinate) {
        super(width, height, xCoordinate, yCoordinate);
    }

    @Override
    public boolean isHit(int x, int y) {
        return hits[y - getyCoordinate()][x - getxCoordinate()]==1;
    }
}
