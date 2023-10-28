import java.util.*;
import java.util.ArrayList;

 class Spot {
    private  Piece piece;
    private int x;
    private int y;

    public Spot(int x, int y, Piece piece) {
        this.piece = piece;
        this.x = x;
        this.y = y;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setPiece(Piece p) {
        this.piece = p;
    }
}

abstract class Piece {
    private boolean killed = false;
    private boolean white = false;

    public Piece(boolean white) {
        this.setWhite(white);
    }

    public boolean isWhite() {
        return this.white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isKilled() {
        return this.killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public abstract boolean validMove(Board board, Spot start, Spot end);
}

class King extends Piece {
    public King(boolean white) {
        super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        if (Math.abs(x1 - x2) == 1 && y1 == y2) {
            return true;
        } else if (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 1) {
            return true;
        } else {
            return false;
        }
    }
}

class Horse extends Piece {
    public Horse(boolean white) {
        super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        int deltaX = Math.abs(x1 - x2);
        int deltaY = Math.abs(y1 - y2);


        return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
    }
}

class Elephant extends Piece {
    public Elephant(boolean white) {
        super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();


        if (x1 == x2 && y1 != y2) {

            int minY = Math.min(y1, y2) + 1;
            int maxY = Math.max(y1, y2);
            for (int y = minY; y < maxY; y++) {
                if (board.getBox(x1, y).getPiece() != null) {
                    return false;
                }
            }
        } else if (y1 == y2 && x1 != x2) {

            int minX = Math.min(x1, x2) + 1;
            int maxX = Math.max(x1, x2);
            for (int x = minX; x < maxX; x++) {
                if (board.getBox(x, y1).getPiece() != null) {
                    return false;
                }
            }
        } else {
            return false;
        }


        if (end.getPiece() == null || end.getPiece().isWhite() != start.getPiece().isWhite()) {
            return true;
        }

        return false;
    }

}

class Soldier extends Piece {
    public Soldier(boolean white) {
        super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        boolean isWhite = start.getPiece().isWhite();


        if (x2 < 0 || x2 > 7 || y2 < 0 || y2 > 7) {
            return false;
        }

        if(isWhite){
            if(y1==y2){
                if(x2-x1==1){
                    return true;
                }
            }
            else{
                if(x2-x1==1&&(y2-y1==1||y2-y1==-1)){
                    return true;
                }
            }
        }
        else{
            if(y1==y2){
                if(x2-x1==-1){
                    return true;
                }
            }
            else{
                if(x2-x1==-1&&(y2-y1==1||y2-y1==-1)){
                    return true;
                }
            }
        }

        return false;
    }

}

class Camel extends Piece {
    public Camel(boolean white) {
        super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
            return true;
        } else {
            return false;
        }
    }
}

class Queen extends Piece {
    public Queen(boolean white) {
        super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        if (x1 == x2 && y1 != y2) {
            return true;
        } else if (x1 != x2 && y1 == y2) {
            return true;
        } else if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
            return true;
        } else {
            return false;
        }
    }
}

class Board {
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

enum PlayerType {
    HUMAN,
    COMPUTER
}

 class Player {
    public boolean whiteSide;
    protected PlayerType playerType;
    protected boolean isCheckmated;

    public Player(boolean whiteSide, PlayerType playerType) {
        this.whiteSide = whiteSide;
        this.playerType = playerType;
    }

    public boolean isWhiteSide() {
        return this.whiteSide;
    }

    public PlayerType getPlayerType() {
        return this.playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public boolean isCheckmated() {
        return isCheckmated;
    }

    public void setCheckmated(boolean checkmated) {
        isCheckmated = checkmated;
    }
}

class HumanPlayer extends Player {
    public HumanPlayer(boolean whiteSide) {
        super(whiteSide, PlayerType.HUMAN);
    }
}

class ComputerPlayer extends Player {
    public ComputerPlayer(boolean whiteSide) {
        super(whiteSide, PlayerType.COMPUTER);
    }
}

class Move {
    private Player player;
    private Spot start;
    private Spot end;
    private Piece pieceMoved;
    private Piece pieceKilled;
    boolean castlingMove = false;

    public Move(Player player, Spot start, Spot end) {
        this.player = player;
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
    }

    public boolean isCastlingMove() {
        return this.castlingMove;
    }

    public void setCastlingMove(boolean castlingMove) {
        this.castlingMove = castlingMove;
    }

    public Spot getStart() {
        return start;
    }

    public Spot getEnd() {
        return end;
    }

    public void setPieceKilled(Piece restPiece) {
        this.pieceKilled = restPiece;
    }
}

enum GameStatus {
    ACTIVE,
    BLACK_WIN,
    WHITE_WIN,
    FORFEIT,
    STALEMATE,
    RESIGNATION
}

class Game {
    private Player[] players = new Player[2];
    public  Board board;
    private Player currentTurn;
    private GameStatus status;
    private ArrayList<Move> movesPlayed = new ArrayList<Move>();

    public Game() {
        this.board = new Board();
    }

     void initialize(Player p1, Player p2) {
        players[0] = p1;
        players[1] = p2;

        board.resetBoard();

        if (p1.isWhiteSide()) {
            this.currentTurn = p1;
        } else {
            this.currentTurn = p2;
        }

        movesPlayed.clear();
    }

    public GameStatus getStatus() {
        return this.status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public boolean isEnd() {
        return this.getStatus() != GameStatus.ACTIVE;
    }

    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) {
        Spot startBox = board.getBox(startX, startY);
        Spot endBox = board.getBox(endX, endY);
        Move move = new Move(player, startBox, endBox);
        return this.makeMove(move, player);
    }

    private boolean makeMove(Move move, Player player) {
        Piece sourcePiece = move.getStart().getPiece();

        if (sourcePiece == null) {
            return false;
        }

        if (player != currentTurn) {
            return false;
        }

        if (sourcePiece.isWhite() != player.isWhiteSide()) {
            return false;
        }

        if (!sourcePiece.validMove(board, move.getStart(), move.getEnd())) {
            return false;
        }

        Piece restPiece = move.getEnd().getPiece();

        if (restPiece != null) {
            restPiece.setKilled(true);
            move.setPieceKilled(restPiece);
        }

        movesPlayed.add(move);

        move.getEnd().setPiece(move.getStart().getPiece());
        move.getStart().setPiece(null);

        if ( restPiece instanceof King) {
            if (player.isWhiteSide()) {
                this.setStatus(GameStatus.WHITE_WIN);
            } else {
                this.setStatus(GameStatus.BLACK_WIN);
            }
        }

        if (this.currentTurn == players[0]) {
            this.currentTurn = players[1];
        } else {
            this.currentTurn = players[0];
        }

        return true;
    }

    public Player getCurrentTurn() {
        return this.currentTurn;
    }
}

public class Main {

    private static void displayBoard(Game game) {
        Spot[][] spots = game.board.getBoxes();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Spot spot = spots[i][j];
                Piece piece = spot.getPiece();

                if (piece == null) {
                    // Empty spot
                    System.out.print("- ");
                } else {
                    // Display the piece based on its type
                    if (piece instanceof King) {
                        System.out.print(piece.isWhite() ? "K " : "k ");
                    } else if (piece instanceof Queen) {
                        System.out.print(piece.isWhite() ? "Q " : "q ");
                    } else if (piece instanceof Elephant) {
                        System.out.print(piece.isWhite() ? "E " : "e ");
                    } else if (piece instanceof Camel) {
                        System.out.print(piece.isWhite() ? "C " : "c ");
                    } else if (piece instanceof Horse) {
                        System.out.print(piece.isWhite() ? "H " : "h ");
                    } else if (piece instanceof Soldier) {
                        System.out.print(piece.isWhite() ? "S " : "s ");
                    }
                }
            }
            System.out.println(); // Move to the next row
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Game game = new Game();
        Player player1 = new HumanPlayer(true);
        Player player2 = new HumanPlayer(false);

        Board board = new Board();

        game.initialize(player1, player2);
        game.setStatus(GameStatus.ACTIVE);

        while (!game.isEnd()) {
            displayBoard(game);

            System.out.println("Player " + (game.getCurrentTurn() == player1 ? "1 (White)" : "2 (Black)") + ", enter your move:");
            System.out.println("Enter the position of the piece to move (e.g., startX startY):");


            int startX = scanner.nextInt();
            int startY = scanner.nextInt();

            System.out.println("Enter the position to move the piece to (e.g., endX endY):");
            int endX = scanner.nextInt();
            int endY = scanner.nextInt();

            boolean validMove = game.playerMove(game.getCurrentTurn(), startX, startY, endX, endY);

            if (validMove) {
                System.out.println("Valid move. Continuing to the next turn.");
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        displayBoard(game);
        System.out.println("Game over! Result: " + game.getStatus());
    }
}