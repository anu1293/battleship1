abstract class Ship {
    private int width;
    private int height;
    private int xCoordinate;
    private int yCoordinate;

    public int getHitPerPart() {
        return hitPerPart;
    }

    protected int hitPerPart=1;
    protected int[][] hits;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int[][] getHits() {
        return hits;
    }

    public Ship(int width, int height, int xCoordinate, int yCoordinate) {
        setWidth(width);
        setHeight(height);
        setxCoordinate(xCoordinate);
        setyCoordinate(yCoordinate);
        this.hits = new int[height][width];
    }

    public abstract boolean isHit(int x, int y);

    public void markHit(int x, int y) {
        hits[y - getyCoordinate()][x - getxCoordinate()] = hits[y - getyCoordinate()][x - getxCoordinate()]+1;
    }

    public boolean isDestroyed() {
        for (int[] row : hits) {
            for (int hit : row) {
                if (hit<hitPerPart) {
                    return false;
                }
            }
        }
        return true;
    }
}
