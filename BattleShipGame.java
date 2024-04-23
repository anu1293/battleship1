import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class BattleShipGame {
    int battleAreaWidth ;
    int battleAreaHeight;
    int numShips;
    Player[] players;
    Scanner scanner;

    public BattleShipGame(Scanner sc)  {
        this.scanner = sc;
    }

    public int getBattleAreaWidth() {
        return battleAreaWidth;
    }

    public int getBattleAreaHeight() {
        return battleAreaHeight;
    }

    public int getNumShips() {
        return numShips;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void setNumShips(int numShips) throws Exception {
        if(numShips>=1 && numShips<=this.battleAreaHeight*this.battleAreaWidth)
            this.numShips = numShips;
        else throw new Exception("NO. of ships should be >1 and less than :"+this.battleAreaHeight*this.battleAreaWidth);
    }

    public void setBattleAreaWidth(int battleAreaWidth) throws Exception {
        if(battleAreaWidth>=1 && battleAreaWidth<=9)
            this.battleAreaWidth = battleAreaWidth;
        else {
            throw new Exception("allowed width is between 1 to 9");
        }
    }

    public void setBattleAreaHeight(int battleAreaHeight) throws Exception {
        if(battleAreaHeight>=1 && battleAreaHeight<=26)
            this.battleAreaHeight = battleAreaHeight;
        else {
            throw new Exception("allowed height is between A to Z");
        }
    }

    public void createShips() throws Exception {
        for (int i = 0; i < numShips; i++) {
            String[] shipInfo = scanner.nextLine().split(" ");
            ShipType shipType = ShipType.valueOf(shipInfo[0]);
            int width = Integer.parseInt(shipInfo[1]);
            int height = Integer.parseInt(shipInfo[2]);
            for (int j = 0; j < players.length; j++) {
                int yCoordinate = shipInfo[j+3].charAt(0) - 'A' + 1;
                int xCoordinate = Integer.parseInt(shipInfo[j+3].substring(1, 2));
                players[j].addShip(ShipFactory.createShip(shipType, width, height, xCoordinate, yCoordinate));
            }
        }
    }

    public void addMissileSequenceForPlayers() {
        for(int i = 0; i < players.length; i++) {
            for(String seq: Arrays.asList(scanner.nextLine().split(" "))) {
                players[i].getMissileSequence().add(seq);
            }
        }
    }

    public void play() {
        DisplayBoard.display(players);
        int currentPlayerIndex = 0;
        int totalPlayersAlive = players.length;
        int totalMissiles = getTotalMissiles();
        while (totalPlayersAlive > 1 && totalMissiles > 0) {
            Player currentPlayer = players[currentPlayerIndex];
            List<String> missileSequence =  currentPlayer.getMissileSequence();

            while(totalPlayersAlive>1 && missileSequence.size() > 0 && currentPlayer.getRemainingShips()>0) {
                String missile = missileSequence.get(0);
                int x = Integer.parseInt(missile.substring(1, 2));
                int y = missile.charAt(0)-'A' +1;
                Player opponentPlayer = getOpponentPlayer(currentPlayerIndex);
                boolean isHit = currentPlayer.fireMissile(x, y, opponentPlayer);
                missileSequence.remove(0);
                totalMissiles = getTotalMissiles();
                if (isHit) {
                    totalPlayersAlive = Arrays.stream(players).filter(player->player.getRemainingShips()>0).mapToInt(player->1).sum();
                    System.out.println("Player " + (currentPlayerIndex + 1) + " fires a missile with target " + missile + ": hit");
                    DisplayBoard.display(opponentPlayer);
                } else {
                    System.out.println("Player " + (currentPlayerIndex + 1) + " fires a missile with target " + missile + ": miss");
                    break;
                }

            }
            currentPlayerIndex =(currentPlayerIndex + 1) % players.length;

        }
        getWinner();
        DisplayBoard.display(players);
    }

    public void getWinner() {
        int count=0,index=-1;
        for(int i=0;i< players.length;i++) {
            if(players[i].getRemainingShips()>0) {
                count++;
                index=i+1;
            }
        }
        if(count==1) {
            System.out.println("player: "+index+" wins");
        } else {
            System.out.println("game draw");
        }
    }

    public abstract void createPlayers();
    public abstract Player getOpponentPlayer(int currentPlayerIndex);
    public abstract int getTotalMissiles();

}

