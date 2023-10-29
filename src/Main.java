import java.util.*;
import chess.*;
import player.*;

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