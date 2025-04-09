
// Emilie Spindler

// Assassin

//Rules of my piece: moves like a queen, but can only move to capture a piece of the opponent's. The piece can pass over another peice of the same color,
// but cannot share or take a square with a piece that has the same color (obiously lol)

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Assassin extends Piece {

  public Assassin(boolean isWhite, String img_file) {
    super(isWhite, img_file);
  }

  // precondition: the piece calling this function has been initialized
  // postcondition: returns a string stating what color the piece is and the type.
  // EX: "A black assassin"
  public String toString() {

    return "A " + super.toString() + " Assassin";
  }

  // TO BE IMPLEMENTED!
  // return a list of every square that is "controlled" by this piece. A square is
  // controlled
  // if the piece capture into it legally.

  // precondition: the pieces and the board have been created
  // postconditon: returns an array of squares that the selected piece can control
  // and can move to
  public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
    ArrayList<Square> possibleLegalMoves = new ArrayList<Square>();

    // vertical below
    for (int r = start.getRow() + 1; r <= 7; r++) {
      Square legalSquare = board[r][start.getCol()];
      if (legalSquare.isOccupied()) {
        possibleLegalMoves.add(legalSquare);
        break;
      }
    }

    // vertical above
    for (int r = start.getRow() - 1; r >= 0; r--) {
      Square legalSquare = board[r][start.getCol()];
      if (legalSquare.isOccupied()) {
        possibleLegalMoves.add(legalSquare);
        break;
      }
    }

    // horizontal left
    for (int c = start.getCol() - 1; c >= 0; c--) {
      Square legalSquare = board[start.getRow()][c];
      if (legalSquare.isOccupied()) {
        possibleLegalMoves.add(legalSquare);
        break;
      }
    }

    // horizontal right
    for (int c = start.getCol() + 1; c <= 7; c++) {
      Square legalSquare = board[start.getRow()][c];
      if (legalSquare.isOccupied()) {
        possibleLegalMoves.add(legalSquare);
        break;
      }
    }

    // diagonal above & right

    for (int r = start.getRow() - 1, c = start.getCol() + 1; r > 0; r--, c++) {
      if (c >= 8) {
        break;
      }
      Square legalSquare = board[r][c];
      if (legalSquare.isOccupied()) {
        possibleLegalMoves.add(legalSquare);
        break;
      }
    } 

    // diagonal above & left
    for (int r = start.getRow() - 1, c = start.getCol() - 1; r > 0; r--, c--) {
      if (c < 0) {
        break;
      }
      Square legalSquare = board[r][c];
      if (legalSquare.isOccupied()) {
        possibleLegalMoves.add(legalSquare);
        break;
      }
    }

    // diagonal below & right
    for (int r = start.getRow() + 1, c = start.getCol() + 1; r < 8; r++, c++) {
      if (c >= 8) {
        break;
      }
      Square legalSquare = board[r][c];
      if (legalSquare.isOccupied()) {
        possibleLegalMoves.add(legalSquare);
        break;
      }
    }

    // diagonal below and left
    for (int r = start.getRow() + 1, c = start.getCol() - 1; r < 8; r++, c--) {
      if (c < 0) {
        break;
      }
      Square legalSquare = board[r][c];
      if (legalSquare.isOccupied()) {
        possibleLegalMoves.add(legalSquare);
        break;
      }
    }

    return possibleLegalMoves;
  }

  // TO BE IMPLEMENTED!
  // implement the move function here
  // it's up to you how the piece moves, but at the very least the rules should be
  // logical and it should never move off the board!
  // returns an arraylist of squares which are legal to move to
  // please note that your piece must have some sort of logic. Just being able to
  // move to every square on the board is not
  // going to score any points.

  // precondition: the board and pieces have been created
  // postcondition: returns an array of squares where the selected piece is able
  // to move to
  public ArrayList<Square> getLegalMoves(Board b, Square start) {
    ArrayList<Square> possibleLegalMoves = new ArrayList<Square>();

    // vertical below
    for (int r = start.getRow() + 1; r <= 7; r++) {
      Square legalSquare = b.getSquareArray()[r][start.getCol()];
      if (legalSquare.isOccupied() && legalSquare.getOccupyingPiece().getColor() != super.getColor()) {
        possibleLegalMoves.add(legalSquare);
        break;
      } else if (legalSquare.isOccupied() && legalSquare.getOccupyingPiece().getColor() == super.getColor()) {
        break;
      }
    }

    // vertical above
    for (int r = start.getRow() - 1; r >= 0; r--) {
      Square legalSquare = b.getSquareArray()[r][start.getCol()];
      if (legalSquare.isOccupied() && legalSquare.getOccupyingPiece().getColor() != super.getColor()) {
        possibleLegalMoves.add(legalSquare);
        break;
      } else if (legalSquare.isOccupied() && legalSquare.getOccupyingPiece().getColor() == super.getColor()) {
        break;
      }
    }

    // horizontal left
    for (int c = start.getCol() - 1; c >= 0; c--) {
      Square legalSquare = b.getSquareArray()[start.getRow()][c];
      if (legalSquare.isOccupied() && legalSquare.getOccupyingPiece().getColor() != super.getColor()) {
        possibleLegalMoves.add(legalSquare);
        break;
      } else if (legalSquare.isOccupied() && legalSquare.getOccupyingPiece().getColor() == super.getColor()) {
        break;
      }
    }

    // horizontal right
    for (int c = start.getCol() + 1; c <= 7; c++) {
      Square legalSquare = b.getSquareArray()[start.getRow()][c];
      if (legalSquare.isOccupied() && legalSquare.getOccupyingPiece().getColor() != super.getColor()) {
        possibleLegalMoves.add(legalSquare);
        break;
      } else if (legalSquare.isOccupied() && legalSquare.getOccupyingPiece().getColor() == super.getColor()) {
        break;
      }
    }

    // diagonal above & right

    for (int r = start.getRow() - 1, c = start.getCol() + 1; r > 0; r--, c++) {
      if (c >= 8) {
        break;
      }
      Square legalSquare = b.getSquareArray()[r][c];
      if (legalSquare.isOccupied() && legalSquare.getOccupyingPiece().getColor() != super.getColor()) {
        possibleLegalMoves.add(legalSquare);
        break;
      } else if (legalSquare.isOccupied() && legalSquare.getOccupyingPiece().getColor() == super.getColor()) {
        break;
      }
    }

    // diagonal above & left
    for (int r = start.getRow() - 1, c = start.getCol() - 1; r > 0; r--, c--) {
      if (c < 0) {
        break;
      }
      Square legalSquare = b.getSquareArray()[r][c];
      if (legalSquare.isOccupied() && legalSquare.getOccupyingPiece().getColor() != super.getColor()) {
        possibleLegalMoves.add(legalSquare);
        break;
      } else if (legalSquare.isOccupied() && legalSquare.getOccupyingPiece().getColor() == super.getColor()) {
        break;
      }
    }

    // diagonal below & right
    for (int r = start.getRow() + 1, c = start.getCol() + 1; r < 8; r++, c++) {
      if (c >= 8) {
        break;
      }
      Square legalSquare = b.getSquareArray()[r][c];

      if (legalSquare.isOccupied() && legalSquare.getOccupyingPiece().getColor() != super.getColor()) {
        possibleLegalMoves.add(legalSquare);
        break;
      } else if (legalSquare.isOccupied() && legalSquare.getOccupyingPiece().getColor() == super.getColor()) {
        break;
      }
    }

    // diagonal below and left
    for (int r = start.getRow() + 1, c = start.getCol() - 1; r < 8; r++, c--) {
      if (c < 0) {
        break;
      }
      Square legalSquare = b.getSquareArray()[r][c];
      if (legalSquare.isOccupied() && legalSquare.getOccupyingPiece().getColor() != super.getColor()) {
        possibleLegalMoves.add(legalSquare);
        break;
      } else if (legalSquare.isOccupied() && legalSquare.getOccupyingPiece().getColor() == super.getColor()) {
        break;
      }
    }

    return possibleLegalMoves;
  }

}
