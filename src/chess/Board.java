package chess;

public class Board {
    Spot[][] checks;

    public Board() {
        checks = new Spot[8][8];
        resetBoard();
    }
    public Spot[][] getBoxes() {
        return this.checks;
    }

    public Spot getBox(int x, int y) {
        try {
            return checks[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void resetBoard() {
        checks[0][0] = new Spot(0, 0, new Elephant(true));
        checks[0][1] = new Spot(0, 1, new Horse(true));
        checks[0][2] = new Spot(0, 3, new Camel(true));
        checks[0][3] = new Spot(0, 4, new Queen(true));
        checks[0][4] = new Spot(0, 5, new King(true));
        checks[0][5] = new Spot(0, 6, new Camel(true));
        checks[0][6] = new Spot(0, 7, new Horse(true));
        checks[0][7] = new Spot(0, 8, new Elephant(true));

        checks[1][0] = new Spot(1, 0, new Soldier(true));
        checks[1][1] = new Spot(1, 1, new Soldier(true));
        checks[1][2] = new Spot(1, 2, new Soldier(true));
        checks[1][3] = new Spot(1, 3, new Soldier(true));
        checks[1][4] = new Spot(1, 4, new Soldier(true));
        checks[1][5] = new Spot(1, 5, new Soldier(true));
        checks[1][6] = new Spot(1, 6, new Soldier(true));
        checks[1][7] = new Spot(1, 7, new Soldier(true));

        checks[7][0] = new Spot(7, 0, new Elephant(false));
        checks[7][1] = new Spot(7, 1, new Horse(false));
        checks[7][2] = new Spot(7, 2, new Camel(false));
        checks[7][3] = new Spot(7, 3, new Queen(false));
        checks[7][4] = new Spot(7, 4, new King(false));
        checks[7][5] = new Spot(7, 5, new Camel(false));
        checks[7][6] = new Spot(7, 6, new Horse(false));
        checks[7][7] = new Spot(7, 7, new Elephant(false));

        checks[6][0] = new Spot(6, 0, new Soldier(false));
        checks[6][1] = new Spot(6, 1, new Soldier(false));
        checks[6][2] = new Spot(6, 2, new Soldier(false));
        checks[6][3] = new Spot(6, 3, new Soldier(false));
        checks[6][4] = new Spot(6, 4, new Soldier(false));
        checks[6][5] = new Spot(6, 5, new Soldier(false));
        checks[6][6] = new Spot(6, 6, new Soldier(false));
        checks[6][7] = new Spot(6, 7, new Soldier(false));

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                checks[i][j] = new Spot(i, j, null);
            }
        }
    }
}






