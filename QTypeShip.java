public class QTypeShip extends Ship {

    public QTypeShip(int width, int height, int xCoordinate, int yCoordinate) {
        super(width, height, xCoordinate, yCoordinate);
        hitPerPart =2;
    }

    @Override
    public boolean isHit(int x, int y) {
        return hits[y - getyCoordinate()][x - getxCoordinate()]<=2 && hits[y - getyCoordinate()][x - getxCoordinate()]>=1;
    }
}
