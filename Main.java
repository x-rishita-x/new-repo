import java.util.*;
import java.lang.Exception;

//spot represents a cell on the chess board
//check all the valid moves equations in the end , they might have error
//edit the king class,
//class Game, Game status, Move, Player and PlayerEngine need to be made
// when to use enum and when to use class (Game status class)?

class Spot {
         // the return type of this attribute is Piece , we made
         // an object of the class so that we can access all the methods of the piece class
         //and set the piece on the spot accordingly
         private Piece piece;
         private int x;
         private int y;

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
}

 abstract class Piece {

     //We have 8 Pawns, 2 Rooks-elephant, 2 Bishops-camel
     //2 Knights-horse, 1 Queen, 1 King for a single colour
     //two colours-black and white,
     //white then boolean returns true
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

     //This method was made abstract as we need to implement it differently for different pieces
     //Further this checks whether a valid move was made or not on the Board
     //This returns true if a valid move was made and false if the move was not valid
     //This method would probably be used in the Game and Game status class to check the valid moves
     // and keep history of the moves being made
     public abstract boolean validMove(Board board, Spot start, Spot end);
 }

class King extends Piece{

    // add the win or loose game accordingly in the king piece here as the
    // killing of the king means lost game

    // call the constructor and if we pass true in the colour then the piece
    // is white otherwise it is black
    public King(boolean white ){
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
    // whenever the constructor of the Board class is called the
    // board gets reset to the initial position with all the pieces at their initial position
    // this helps us in resetting the game to a new game

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
        checks[7][0]=new Spot(0,0,new Elephant(true));
        checks[7][1]=new Spot(0,1,new Horse(true));
        checks[7][2]=new Spot(0,3,new Camel(true));
        checks[7][3]=new Spot(0,4,new Queen(true));
        checks[7][4]=new Spot(0,5,new King(true));
        checks[7][5]=new Spot(0,6,new Camel(true));
        checks[7][6]=new Spot(0,7,new Horse(true));
        checks[7][7]=new Spot(0,8,new Elephant(true));

        checks[6][0]=new Spot(1,0,new Soldier(true));
        checks[6][1]=new Spot(1,1,new Soldier(true));
        checks[6][2]=new Spot(1,2,new Soldier(true));
        checks[6][3]=new Spot(1,3,new Soldier(true));
        checks[6][4]=new Spot(1,4,new Soldier(true));
        checks[6][5]=new Spot(1,5,new Soldier(true));
        checks[6][6]=new Spot(1,6,new Soldier(true));
        checks[6][7]=new Spot(1,7,new Soldier(true));

        //The remaining boxes are initialised as Null
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                checks[i][j] = new Spot(i, j, null);
            }
        }
    }
 }



 abstract class Player{
    //Player: An abstract class for player, it can be a human or a computer.
    //this would contain two methods computer and human player
     // set the values whether human or computer through constructor
}
class  Game{
  // The game engine for computer playeer chess
}
class Move{
    //Move: To represent a chess move:
    //keeps track of which player is moving and what piece

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



public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
    }
}