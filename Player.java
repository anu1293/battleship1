import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Player {
    private int battleAreaWidth;
    private int battleAreaHeight;
    private List<Ship> ships;
    private int remainingShips;
    private List<String> missileSequence;

    public Player(int battleAreaWidth, int battleAreaHeight, int numShips) {
        this.battleAreaWidth = battleAreaWidth;
        this.battleAreaHeight = battleAreaHeight;
        this.ships = new ArrayList<>();
        this.remainingShips = numShips;
        this.missileSequence = new ArrayList<>();
    }

    public List<Ship> getShips() {
        return ships;
    }

    public int getBattleAreaWidth() {
        return battleAreaWidth;
    }

    public int getBattleAreaHeight() {
        return battleAreaHeight;
    }

    public void setRemainingShips(int remainingShips) {
        this.remainingShips = remainingShips;
        if(remainingShips==0) {
            setMissileSequence(Collections.emptyList());
        }
    }

    public List<String> getMissileSequence() {
        return missileSequence;
    }

    public void setMissileSequence(List<String> missileSequence) {
        this.missileSequence = missileSequence;
    }

    public void addShip(Ship ship) throws Exception {
        if(checkShipCoordinateOverlap(ship) && isValidCoordinate(ship))
            ships.add(ship);
    }

    private boolean isValidCoordinate(Ship ship) throws Exception {
         if(ship.getxCoordinate()+ship.getWidth()-1<=battleAreaWidth && ship.getHeight()+ship.getyCoordinate()-1<=battleAreaHeight
         && ship.getxCoordinate()>0 && ship.getyCoordinate()>0) {
            return true;
        }
         throw new Exception("coordinate outside battle field");
    }

    private boolean checkShipCoordinateOverlap(Ship newShip) throws Exception {
        for(Ship ship: this.ships) {
            if(!(newShip.getxCoordinate()+newShip.getWidth()-1<ship.getxCoordinate() ||
                    newShip.getxCoordinate()>ship.getxCoordinate()+ship.getWidth()-1 ||
                    newShip.getyCoordinate()+newShip.getHeight()-1<ship.getyCoordinate() ||
                    newShip.getyCoordinate()>ship.getyCoordinate()+ship.getHeight()-1)) {
                throw new Exception("coordinate overlap with other ship");
            }

        }
        return true;
    }

    public int getRemainingShips() {
        return remainingShips;
    }

    public boolean fireMissile(int x, int y, Player opponent) {
        for (Ship ship : opponent.ships) {
            if (x >= ship.getxCoordinate() && x < ship.getxCoordinate() + ship.getWidth() &&
                    y >= ship.getyCoordinate() && y < ship.getyCoordinate() + ship.getHeight()) {
                ship.markHit(x, y);
                if (ship.isDestroyed() && ship.isHit(x,y)) {
                    opponent.setRemainingShips(opponent.getRemainingShips()-1);
                }
                return ship.isHit(x,y);
            }
        }
        return false;
    }
}