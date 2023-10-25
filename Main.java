import java.util.*;
import java.util.ArrayList;



//spot represents a cell on the chess board
//check all the valid moves equations in the end , they might have error
//edit the king class,
//class Game, Game status, Move, Player and PlayerEngine need to be made
// when to use enum and when to use class (Game status class)?
//Take input from the player whether they want the computer or the human player and accordingly call the constructor

//Spot: A spot represents one block of the 8×8 grid and an optional piece.
//Piece: The basic building block of the system, every piece will be placed on a spot. Piece class is an abstract class. The extended classes (Pawn, King, Queen, Rook, Knight, Bishop) implements the abstracted operations.
//Board: Board is an 8×8 set of boxes containing all active chess pieces.
//Player: Player class represents one of the participants playing the game.
//Move: Represents a game move, containing the starting and ending spot. The Move class will also keep track of the player who made the move.
//Game: This class controls the flow of a game. It keeps track of all the game moves, which player has the current turn, and the final result of the game.

//see the special move castling in the end of the king and rook involving


//Here's a breakdown of your classes and how they fit into a chess game:
//
//1. `Spot`: Represents a cell on the chessboard and can contain a chess piece.
//
//2. `Piece`: An abstract class serving as the base for specific chess piece classes (e.g., `King`, `Horse`, `Elephant`, etc.). It provides common properties like color and whether the piece has been killed. Each specific piece class must implement its `validMove` method to define how that piece can move.
//
//3. Specific piece classes (e.g., `King`, `Horse`, `Elephant`, etc.): Extend the `Piece` class and define the specific rules for each type of chess piece. These classes override the `validMove` method to specify how the piece can move.
//
//4. `Board`: Represents the chessboard as an 8x8 grid of `Spot` objects. It initializes the board and resets it to the starting position.
//
//5. `Player`: An abstract class serving as the base for `HumanPlayer` and `ComputerPlayer` classes. It defines whether the player is on the white side and whether the player is human or computer-controlled.
//
//6. `HumanPlayer` and `ComputerPlayer`: Subclasses of `Player` representing human and computer players, respectively. They specify the player's characteristics.
//
//7. `Move`: Represents a chess move, including the player, starting spot, ending spot, and any captured pieces. It also tracks castling moves.
//
//8. `GameStatus`: An enum representing the different states of the game, such as `ACTIVE`, `BLACK_WIN`, `WHITE_WIN`, `FORFEIT`, `STALEMATE`, and `RESIGNATION`.
//
//9. `Game`: Represents the game itself. It manages the players, the board, the current turn, the game status, and the moves played. The `initialize` method sets up the game, and the `makeMove` method handles player moves. It also checks for the game's end condition.
//
//10. `Main` class: The entry point of your application, where you can start building your chess game. You can add code to create players, initialize the game, and implement the game loop to allow players to make moves.
//
//Before you can run your chess game, you need to implement the game loop and the user interface for making moves. This code provides the foundation for a chess game, and you can build upon it to create a playable game.
class Spot {
         // the return type of this attribute is Piece , we made
         // an object of the class so that we can access all the methods of the piece class
         // and set the piece on the spot accordingly
         protected Piece piece;
          protected int x;
          protected int y;

         public Spot(int x, int y, Piece piece)
         {
             this.piece=piece;
             this.x = x;
             this.y=y;
         }

         public Piece getPiece()
         {
             return this.piece;
         }
         public int getX()
         {
             return this.x;
         }
         public int getY()
         {
             return this.y;
         }
    public void setPiece(Piece p)
    {
        this.piece=p;
    }
}
//Piece class is kept abstract sp that is serves as a base class for the other players like king.queen etc
// This way we can create a common link between all the pieces and also ensure some common functionalities
 abstract class Piece {

     //We have 8 Pawns, 2 Rooks-elephant, 2 Bishops-camel
     //2 Knights-horse, 1 Queen, 1 King for a single colour
     //two colours-black and white,
     //if colour white then boolean returns true
     private boolean killed = false;
     //by default the piece is not killed
     private boolean white = false;
     // sets the colour as white and returns true if white
     // and default colour set is black
     //we have false for black and true for white

     public Piece(boolean white) {
         this.setWhite(white);
     }

     //returns colour of the piece
     public boolean isWhite() {
         return this.white;
     }

     public void setWhite(boolean white) {
         this.white = white;
     }

     //returns whether the piece is killed or not
     public boolean isKilled() {
         return this.killed;
     }

     // if the piece is killed then we pass the "true" value of
     // it being killed and then set the killed attribute
     public void setKilled(boolean killed) {
         this.killed = killed;
     }
     public abstract boolean validMove(Board board, Spot start, Spot end);
     //This method was made abstract as we need to implement it differently for different pieces
     //Further this checks whether a valid move was made or not on the Board
     //This returns true if a valid move was made and false if the move was not valid
     //This method would probably be used in the Game and Game status class to check the valid moves
     // and keep history of the moves being made

 }

class King extends Piece{

    // add the win or loose game accordingly in the king piece here as the
    // killing of the king means lost game

    // call the constructor and if we pass true in the colour then the piece
    // is white otherwise it is black
    public King(boolean white){
        super(white);
    }
    @Override
    public boolean validMove(Board board, Spot start, Spot end){
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();
     //A king can move one square in any direction: horizontally, vertically, or diagonally.
     //
     // For horizontal or vertical movement:
        if(Math.abs(x1 - x2) == 1 && y1 == y2){
            return true;
        }
        //For diagonal movement:
        else if(Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 1){
            return true;
        }
        else{
            return false;
        }

    }

}
class Horse extends Piece{
    public Horse(boolean white){
    //pass true if the piece is white
    super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        // we can't move the piece to a spot that has
        // a piece of the same colour
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }
        //end.getPiece(): This part of the code retrieves the chess piece (if any) that is located on the end spot on the chessboard.
        // It accesses the getPiece method in the Spot class  of the end spot object of the spot class
        //
        //end.getPiece().isWhite(): The getPiece() method returns a chess piece, and then the isWhite() method is called on that piece.
        // The isWhite method is presumably a method in the ChessPiece class (or a related class) that checks
        // whether the piece is of the white color. It might return true if the piece is white and false if it's not
        //
        //this.isWhite(): The "this" keyword refers to the current chess piece, which is invoking the canMove method
        // It also presumably has an isWhite method to check its color.

        int x = Math.abs(start.getX() - end.getY());
        int y = Math.abs(start.getY()) - end.getX();
        //This returns the boolean value for the valid move being made
        return x * y == 2;

        //Math.abs is a method from the Java Math class, and it is used to calculate the absolute value of a number.
    }
}
 class Elephant extends Piece{
    public Elephant(boolean white){
    super(white);
    }
    @Override
    public boolean validMove(Board board, Spot start, Spot end){
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();
        if(x1==x2&&y1!=y2){
            return true;
        }
        else if(y1==y2&&x1!=x2){
            return true;
        }
        else{
            return false;
        }
     }
 }

 class Soldier extends Piece{
    public Soldier(boolean white){
    super(white);
    }
     @Override
     public boolean validMove(Board board, Spot start, Spot end){
         int x1 = start.getX();
         int y1 = start.getY();
         int x2 = end.getX();
         int y2 = end.getY();
         //Pawn's First Move (initial double-step): The pawn can move two steps initially
         if (x1 == x2 && (y2 - y1) == 2){
             return true;
         }
         //Pawn's Standard Move (one square forward):
         else if (x1 == x2 && (y2 - y1) == 1){
             return true;
         }
         //Pawn's Capture Move (diagonal capture):
         else if (Math.abs(x2 - x1) == 1 && (y2 - y1) == 1){
             return true;
         }
         else{
             return false;
         }
          //En Passant Capture:
         //This is a special pawn capture move when an opponent's pawn has moved
         // two squares forward from its starting position, passing the capturing pawn.
         //we need history of moves made here to determine this move
         //Include this after making the Game class which contains history of moves
     }
 }
class Camel extends Piece{
    public Camel(boolean white){
        super(white);
    }
     @Override
     public boolean validMove(Board board, Spot start, Spot end){
         int x1 = start.getX();
         int y1 = start.getY();
         int x2 = end.getX();
         int y2 = end.getY();

         if(Math.abs(x1 - x2) == Math.abs(y1 - y2)){
             return true;
         }
         else{
             return false;
         }
     }
 }
 class Queen extends Piece{
        public Queen(boolean white){
        super(white);
        }
     @Override
     public boolean validMove(Board board, Spot start, Spot end){
         int x1 = start.getX();
         int y1 = start.getY();
         int x2 = end.getX();
         int y2 = end.getY();
         //queen in chess is a powerful piece that combines the movement capabilities of an elephant and a camel.
         // It can move horizontally, vertically, and diagonally.
         //
         //Horizontal Movement (Elephant-Like Movement):
         if(x1 == x2 && y1 != y2){
             return true;
         }
         //Vertical Movement (elephant-Like Movement):
         else if(x1 != x2 && y1 == y2){
              return true;
         }
         //Diagonal Movement (Bishop-Like Movement):
         else if(Math.abs(x1 - x2) == Math.abs(y1 - y2)){
             return true;
         }
         else{
             return false;
         }
     }
 }


 class Board{

    // this is a 2-D matrix made by object of the Spot class to represent a board
    Spot checks[][];
    public Board(){
    //initialise the board by 8*8
    checks=new Spot[8][8];
    resetBoard();
    }
     public Spot[][] getBoxes() {
         return this.checks;
     }
    // whenever the constructor of the Board class is called the
    // board gets reset to the initial position with all the pieces at their initial position
    // this helps us in resetting the game to a new game
    public Spot getBox(int x, int y) {
        try {
            return checks[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            // Handle the exception by returning null or throwing a custom exception
            return null;
            // Alternatively, you can throw a custom exception:
            // throw new CustomIndexOutOfBoundsException("Index out of bounds");
        }
    }
     // The spot class had three parameters the x,y and the piece to be set
     //here accordingly the parameters are being passed in the constructor of the Spot class
    public void resetBoard(){
        //we are using new keyword in declaring the pieces here
        // as there are multiple horses or camels for the same colour

    //This is the white colour set
    checks[0][0]=new Spot(0,0,new Elephant(true));
    checks[0][1]=new Spot(0,1,new Horse(true));
    checks[0][2]=new Spot(0,3,new Camel(true));
    checks[0][3]=new Spot(0,4,new Queen(true));
    checks[0][4]=new Spot(0,5,new King(true));
    checks[0][5]=new Spot(0,6,new Camel(true));
    checks[0][6]=new Spot(0,7,new Horse(true));
    checks[0][7]=new Spot(0,8,new Elephant(true));

    checks[1][0]=new Spot(1,0,new Soldier(true));
    checks[1][1]=new Spot(1,1,new Soldier(true));
    checks[1][2]=new Spot(1,2,new Soldier(true));
    checks[1][3]=new Spot(1,3,new Soldier(true));
    checks[1][4]=new Spot(1,4,new Soldier(true));
    checks[1][5]=new Spot(1,5,new Soldier(true));
    checks[1][6]=new Spot(1,6,new Soldier(true));
    checks[1][7]=new Spot(1,7,new Soldier(true));

        //Black colour
        checks[7][0]=new Spot(7,0,new Elephant(false));
        checks[7][1]=new Spot(7,1,new Horse(false));
        checks[7][2]=new Spot(7,2,new Camel(false));
        checks[7][3]=new Spot(7,3,new Queen(false));
        checks[7][4]=new Spot(7,4,new King(false));
        checks[7][5]=new Spot(7,5,new Camel(false));
        checks[7][6]=new Spot(7,6,new Horse(false));
        checks[7][7]=new Spot(7,7,new Elephant(false));

        checks[6][0]=new Spot(6,0,new Soldier(false));
        checks[6][1]=new Spot(6,1,new Soldier(false));
        checks[6][2]=new Spot(6,2,new Soldier(false));
        checks[6][3]=new Spot(6,3,new Soldier(false));
        checks[6][4]=new Spot(6,4,new Soldier(false));
        checks[6][5]=new Spot(6,5,new Soldier(false));
        checks[6][6]=new Spot(6,6,new Soldier(false));
        checks[6][7]=new Spot(6,7,new Soldier(false));

        //The remaining boxes are initialised as Null
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                checks[i][j] = new Spot(i, j, null);
            }
        }
    }
 }


//This is an abstract class, which means it cannot be instantiated on its own
// but serves as a base class for other classes to inherit from i.e. human and computer player

enum PlayerType {
    HUMAN,
    COMPUTER
}
abstract class Player {
    /* we need two variables in the player class first whether the player is human or computer
     and then whether it is playing from the white or the black side */

    //whitePlayers is a boolean flag that indicates whether the player is playing from the white side or the black side
    //humanPlayer is a boolean flag that indicates whether the player is a human or a computer-controlled player.
    public boolean whiteSide;
    //true:white players , false : black players
    private PlayerType playerType;
    private boolean isCheckmated;

    public Player(boolean whiteSide, PlayerType playerType) {
        this.whiteSide = whiteSide;
        this.playerType = playerType;
    }

    public boolean isWhiteSide()
    { //This lets us know whether the player is on white side or black side by returning the true or false value accordingly
        return this.whiteSide;
    }
    public PlayerType getPlayerType() {
        return this.playerType;
    }
    public void setPlayerType(PlayerType playerType) {
        this.playerType=playerType;
    }
    public boolean isCheckmated() {
        return isCheckmated;
    }
    public void setCheckmated(boolean checkmated) {
        isCheckmated = checkmated;
    }

}

 class HumanPlayer extends Player {



    public HumanPlayer(boolean whiteSide)
    {
        super(whiteSide, PlayerType.HUMAN);
    }
}

class ComputerPlayer extends Player {

    public ComputerPlayer(boolean whiteSide) {
        super(whiteSide, PlayerType.COMPUTER);
    }
}




//Move: To represent a chess move:
//keeps track of which player is moving and what piece
class Move{
    //These are objects of the particular class
    protected Player player;
    protected Spot start;
    protected Spot end;
    protected Piece pieceMoved;
    protected Piece pieceKilled;
     boolean castlingMove = false;
    //when the constructor of the move class is called then it asks the player that moved and from where to where
    //It keeps track of all the moves that are being made
    public Move(Player player, Spot start, Spot end)
    {
        this.player = player;
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
        /*The purpose of the line of code is to initialize the pieceMoved property with the game piece that is currently located on the starting spot.
        This allows the Move object to keep track of which piece is being moved from its starting position to its ending position.*/
    }

    public boolean isCastlingMove()
    {
        return this.castlingMove;
    }

    public void setCastlingMove(boolean castlingMove)
    {
        this.castlingMove = castlingMove;
    }
    public Spot getStart(){
        return start;
    }
    public Spot getEnd(){
        return end;
    }
   public void setPieceKilled(Piece restPiece){
        this.pieceKilled=restPiece;
   }


}
 enum GameStatus {
    //check whether it should be class or enum
    ACTIVE,
    BLACK_WIN,
    WHITE_WIN,
    FORFEIT,
    STALEMATE,
    RESIGNATION
}

 class Game {
     protected Player[] players = new Player[2]; //player array where we set two players
     protected  Board board;
     protected  Player currentTurn;
     protected  GameStatus status;
     protected  ArrayList<Move> movesPlayed=new ArrayList<>();

     public Game(){
         this.board = new Board();
     }

     void initialize(Player p1, Player p2)//it initialises the game
    {
        players[0] = p1;
        players[1] = p2;

        board.resetBoard();//we reset the board to initial positions

        if (p1.isWhiteSide()) {//the return value of this method is boolean it returns whether the player is playing from the white or the black side
            this.currentTurn = p1;
            //the white side has its turn first therefore it checks whether p1 is white or p2 and accordingly gives the first turn
        }
        else {
            this.currentTurn = p2;
        }

        movesPlayed.clear();//since we are starting a new game we make sure that we do not have any previous game moves recorded
    }
     public GameStatus getStatus()
     {
         return this.status;
     }

     public void setStatus(GameStatus status)//this sets the type of status the game is in from the different options given in Game status class
     {
         this.status = status;
     }
    public boolean isEnd()//checks whether the game has ended or not if the game status class . active return true this means that game is still active therefore the game has not ended and false value should go into since game has not ended and therefore the function returns false value
    {
        return this.getStatus() != GameStatus.ACTIVE;
    }

    //This method is called when player attempts to make a move
    public boolean playerMove(Player player, int startX,
                              int startY, int endX, int endY)
    {
        Spot startBox = board.getBox(startX, startY);//we set the start box
        Spot endBox = board.getBox(endX, endY);//we set the end box corresponding to the player
        Move move = new Move(player, startBox, endBox);
        return this.makeMove(move, player);
    }
    //This method is called when a player attempts to make a move.
    private boolean makeMove(Move move, Player player)//false: move is invalid //true: move is valid
    {   //This is the core method responsible for executing a move.
        //This is a private method that takes a Move object and the current player making the move as parameters. It returns a boolean value to indicate whether the move was successful.
        //Source piece is which in currently undergoing move

        Piece sourcePiece = move.getStart().getPiece();//any object of piece by extension means that horse, king etc. This line retrieves the chess piece (e.g., a king, pawn, etc.) from the starting spot of the move. It assumes that the Spot class has a getPiece() method to get the piece located on a spot.

        //getStart() that returns the starting Spot associated with the move.
        //This checks if there is a chess piece on the starting spot. If there isn't, the move is considered invalid, and false is returned.
        if (sourcePiece == null) {
            return false;
        }

        // This checks if the player making the move is the same as the current player whose turn it is. If not, the move is invalid, and false is returned.
        if (player != currentTurn) {
            return false;
        }
         //This verifies if the color of the moving piece matches the color of the player making the move. If not, the move is invalid, and false is returned.
        if (sourcePiece.isWhite() != player.isWhiteSide()) {
            return false;
        }

        // valid move?
        if (!sourcePiece.validMove(board, move.getStart(),move.getEnd())) {//we have particular valid moves for each type of piece, this checks whether the move by source piece matches the one which we have written in the equations
            return false;
        }

        //this gets the piece at the destination
        Piece restPiece = move.getEnd().getPiece();

        // kill?
        if (restPiece != null) {//there is a piece already present at the destination
            restPiece.setKilled(true);
            move.setPieceKilled(restPiece);
        }

        // castling?


        // store the move
        movesPlayed.add(move);

        // move piece from the stat box to end box
        move.getEnd().setPiece(move.getStart().getPiece());//the piece at the starting is set at the destination
        move.getStart().setPiece(null);//the initial position is set to null / void

        //If the destination piece is a king, this section of the code checks the color of the player who made the move and updates the game status accordingly.
        // If it's a checkmate, the game status is set to "WHITE_WIN" or "BLACK_WIN."
        if ( restPiece instanceof King) {
            if (player.isWhiteSide()) {
                this.setStatus(GameStatus.WHITE_WIN);
            }
            else {
                this.setStatus(GameStatus.BLACK_WIN);
            }
        }

        // set the current turn to the other player
        if (this.currentTurn == players[0]) {
            this.currentTurn = players[1];//gives the next chance to the other player
        }
        else {
            this.currentTurn = players[0];
        }

        return true;//we took care of all the cases where it could not be valid move hence if this is not the case then we return true
    }
    public Player getCurrentTurn(){
         return this.currentTurn;
    }
}

public class Main {

    private static void displayBoard(Board board) {
        Spot[][] spots = board.getBoxes();

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
        // Create players (you can modify this part for human or computer players)
        Player player1 = new HumanPlayer(true);
        Player player2 = new HumanPlayer(false);

        // Create the chessboard
        Board board = new Board();

        // Create a game instance

        game.initialize(player1, player2);

        // Main game loop
        while (!game.isEnd()) {
            // Display the board
            displayBoard(board);

            // Get user input for the move
            System.out.println("Player " + (game.getCurrentTurn() == player1 ? "1 (White)" : "2 (Black)") + ", enter your move:");

            // Read user input (format: startX startY endX endY)
            int startX = scanner.nextInt();
            int startY = scanner.nextInt();
            int endX = scanner.nextInt();
            int endY = scanner.nextInt();

            // Attempt to make the move
            boolean validMove = game.playerMove(game.getCurrentTurn(), startX, startY, endX, endY);

            if (validMove) {
                System.out.println("Valid move. Continuing to the next turn.");
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        // Display the result of the game
        displayBoard(board);
        System.out.println("Game over! Result: " + game.getStatus());
    }


}