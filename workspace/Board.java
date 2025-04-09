
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
    // Resource location constants for piece images
    private static final String RESOURCES_WBISHOP_PNG = "wbishop.png";
    private static final String RESOURCES_BBISHOP_PNG = "bbishop.png";
    private static final String RESOURCES_WKNIGHT_PNG = "wknight.png";
    private static final String RESOURCES_BKNIGHT_PNG = "bknight.png";
    private static final String RESOURCES_WROOK_PNG = "wrook.png";
    private static final String RESOURCES_BROOK_PNG = "brook.png";
    private static final String RESOURCES_WKING_PNG = "wking.png";
    private static final String RESOURCES_BKING_PNG = "bking.png";
    private static final String RESOURCES_BQUEEN_PNG = "bqueen.png";
    private static final String RESOURCES_WQUEEN_PNG = "wqueen.png";
    private static final String RESOURCES_WPAWN_PNG = "wpawn.png";
    private static final String RESOURCES_BPAWN_PNG = "bpawn.png";

    private static final String RESOURCES_WACE_PNG = "wace.png";
    private static final String RESOURCES_BACE_PNG = "bace.png";

    private static final String RESOURCES_ROOKROCKET_PNG = "blackrocket.png";

    private static final String RESOURCES_WPOPE_PNG = "wpope.png";
    private static final String RESOURCES_BPOPE_PNG = "bpope.png";

    private static final String RESOURCES_WCHECKER_PNG = "wchecker.png";
    private static final String RESOURCES_BCHECKER_PNG = "bchecker.png";

    // Logical and graphical representations of board
    private final Square[][] board;
    private final GameWindow g;

    // contains true if it's white's turn.
    private boolean whiteTurn;

    // if the player is currently dragging a piece this variable contains it.
    private Piece currPiece;
    private Square fromMoveSquare;

    // used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;

    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        // TO BE IMPLEMENTED FIRST

        for (int r = 0; r < board.length; r++) {
            boolean isWhite;
            if (r % 2 == 0) {
                isWhite = true;
            } else {
                isWhite = false;
            }

            for (int c = 0; c < board[r].length; c++) {
                board[r][c] = new Square(this, isWhite, r, c);
                if (isWhite) {
                    isWhite = false;
                } else {
                    isWhite = true;
                }

                this.add(board[r][c]);
                // System.out.println("[" + r + "]" + "[" + c + "]");

            }

        }
        // populate the board with squares here. Note that the board is composed of 64
        // squares alternating from
        // white to black.

        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }

    // set up the board such that the black pieces are on one side and the white
    // pieces are on the other.
    // since we only have one kind of piece for now you need only set the same
    // number of pieces on either side.
    // it's up to you how you wish to arrange your pieces.
    private void initializePieces() {

        // RookRockets
        board[0][0].put(new RookRocket(true, RESOURCES_WROOK_PNG));
        board[0][7].put(new RookRocket(true, RESOURCES_WROOK_PNG));

        board[7][0].put(new RookRocket(false, RESOURCES_BROOK_PNG));
        board[7][7].put(new RookRocket(false, RESOURCES_WROOK_PNG));

        // Checker
        for (int col = 0; col <= 7; col++) {
            board[1][col].put(new Checker(true, RESOURCES_WCHECKER_PNG));
            board[6][col].put(new Checker(false, RESOURCES_BCHECKER_PNG));
        }

        // Kings
        board[0][3].put(new King(true, RESOURCES_WKING_PNG));
        board[7][3].put(new King(false, RESOURCES_BKING_PNG));

        // Ace In the Hole
        board[0][4].put(new Ace(true, RESOURCES_WACE_PNG));
        board[7][4].put(new Ace(false, RESOURCES_BACE_PNG));

        // Assassin
        board[0][2].put(new Assassin(true, RESOURCES_WBISHOP_PNG));
        board[0][5].put(new Assassin(true, RESOURCES_WBISHOP_PNG));

        board[7][2].put(new Assassin(false, RESOURCES_BBISHOP_PNG));
        board[7][5].put(new Assassin(false, RESOURCES_BBISHOP_PNG));

        // POPES
        board[0][1].put(new Pope(true, RESOURCES_WPOPE_PNG));
        board[0][6].put(new Pope(true, RESOURCES_WPOPE_PNG));

        board[7][1].put(new Pope(false, RESOURCES_BPOPE_PNG));
        board[7][6].put(new Pope(false, RESOURCES_BPOPE_PNG));
    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square sq = board[x][y];
                if (sq == fromMoveSquare)
                    sq.setBorder(BorderFactory.createLineBorder(Color.blue));
                sq.paintComponent(g);

            }
        }
        if (currPiece != null) {
            if ((currPiece.getColor() && whiteTurn)
                    || (!currPiece.getColor() && !whiteTurn)) {
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (sq.isOccupied()) {
            currPiece = sq.getOccupyingPiece();
            fromMoveSquare = sq;
            if (!currPiece.getColor() && whiteTurn)
                return;
            if (currPiece.getColor() && !whiteTurn)
                return;
            if (currPiece.getControlledSquares(this.getSquareArray(), fromMoveSquare) != null) {
                for (Square controlledSquare : currPiece.getControlledSquares(board, fromMoveSquare)) {
                    controlledSquare.setBorder(BorderFactory.createLineBorder(Color.magenta));
                }
            }
            sq.setDisplay(false);
        }

        repaint();
    }

    // TO BE IMPLEMENTED!
    // should move the piece to the desired location only if this is a legal move.
    // use the pieces "legal move" function to determine if this move is legal, then
    // complete it by
    // moving the new piece to it's new board location.
    @Override
    public void mouseReleased(MouseEvent e) {

        Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
        Piece endSquarePiece = null; //Possible breaking point
        ArrayList<Piece> bombedSquares = new ArrayList<Piece>();

        if ((currPiece != null) && (currPiece.getColor() == whiteTurn)) {

            if (currPiece instanceof RookRocket) { //If I'm dealing with a rookRocket save all of its blown up squares
                for (int row = endSquare.getRow() - 1; row <= endSquare.getRow() + 1; row++) {
                    for (int col = endSquare.getCol() - 1; col <= endSquare.getCol() + 1; col++) {
                        if ((0 <= row && row <= 7) && (0 <= col && col <= 7)) {
                            bombedSquares.add(board[row][col].getOccupyingPiece());
                        }
                    }
                }
            }
            else {
                endSquarePiece = endSquare.getOccupyingPiece();
            }



            for (Square[] row : board) {
                for (Square s : row) {
                    s.setBorder(null);
                }
            }

            // using currPiece
            ArrayList<Square> legalMoves = currPiece.getLegalMoves(this, fromMoveSquare);
            for (Square move : legalMoves) {
                if (endSquare == move) {
                    if (currPiece instanceof Ace) {
                        fromMoveSquare.put(endSquarePiece);
                    } else if (currPiece instanceof RookRocket) {
                        endSquare.put(fromMoveSquare.getOccupyingPiece());
                        fromMoveSquare.removePiece();
                        if (Math.random() < .2) {
                            if (endSquare.getRow() - 1 >= 0) {
                                board[endSquare.getRow() - 1][endSquare.getCol()].put(null);
                            }
                            if (endSquare.getRow() + 1 <= 7) {
                                board[endSquare.getRow() + 1][endSquare.getCol()].put(null);
                            }
                            if (endSquare.getCol() - 1 >= 0) {
                                board[endSquare.getRow()][endSquare.getCol() - 1].put(null);
                            }
                            if (endSquare.getCol() + 1 <= 7) {
                                board[endSquare.getRow()][endSquare.getCol() + 1].put(null);
                            }
                            if (endSquare.getRow() - 1 >= 0 && endSquare.getCol() - 1 >= 0) {
                                board[endSquare.getRow() - 1][endSquare.getCol() - 1].put(null);
                            }
                            if (endSquare.getRow() - 1 >= 0 && endSquare.getCol() + 1 <= 7) {
                                board[endSquare.getRow() - 1][endSquare.getCol() + 1].put(null);
                            }
                            if (endSquare.getRow() + 1 <= 7 && endSquare.getCol() - 1 >= 0) {
                                board[endSquare.getRow() + 1][endSquare.getCol() - 1].put(null);
                            }
                            if (endSquare.getRow() + 1 <= 7 && endSquare.getCol() + 1 <= 7) {
                                board[endSquare.getRow() + 1][endSquare.getCol() + 1].put(null);
                            }
                            board[endSquare.getRow()][endSquare.getCol()].put(null);
                        }
                    } else { // Other Pieces may operate weirdly as well so this is a generalization
                        fromMoveSquare.removePiece();
                    }
                    endSquare.put(currPiece);
                    // Turns it to the other players turn
                    if (isInCheck(whiteTurn)) {
                        if (currPiece instanceof RookRocket) {
                            int index =0;
                            for (int row = endSquare.getRow() - 1; row <= endSquare.getRow() + 1; row++) {
                                for (int col = endSquare.getCol() - 1; col <= endSquare.getCol() + 1; col++) {
                                    if ((0 <= row && row <= 7) && (0 <= col && col <= 7)) {
                                        board[row][col].put(bombedSquares.get(index));
                                        index++;
                                    }
                                }
                            }
                        }
                        else {
                            endSquare.put(endSquarePiece);
                        }

                        fromMoveSquare.put(currPiece);
                    }
                    /*
                     * else if (theyHaveNoPossibleMoves()) {
                     * g.checkmateOccurred(whiteTurn);
                     * }
                     */
                    else {
                        whiteTurn = !whiteTurn;
                    }
                }
            }

            fromMoveSquare.setDisplay(true);
            currPiece = null;
            repaint();
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - 24;
        currY = e.getY() - 24;

        repaint();
        if (currPiece != null && currPiece.getLegalMoves(this, fromMoveSquare) != null) {

            for (Square s : currPiece.getLegalMoves(this, fromMoveSquare)) {
                s.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // precondition - the board is initialized and contains a king of either color.
    // The boolean kingColor corresponds to the color of the king we wish to know
    // the status of.
    // postcondition - returns true of the king is in check and false otherwise.
    public boolean isInCheck(boolean kingColor) {
        Square kingSquare = null; // Square king is on
        ArrayList<ArrayList<Square>> arrOfControlledSquares = new ArrayList<ArrayList<Square>>(); // Array of controlled
                                                                                                  // Square's arrays
        for (Square[] row : this.getSquareArray()) { // Loops through board for each row
            for (Square sq : row) { // Loops through rows for each square
                if (sq.isOccupied()) { // Check if current square has a piece on it
                    if (sq.getOccupyingPiece() instanceof King && // If piece is a king and is the same color as the
                                                                  // inputted parameter make its square the kingSquare
                            sq.getOccupyingPiece().getColor() == kingColor) {
                        kingSquare = sq;
                    }

                    else if (sq.getOccupyingPiece().getColor() != kingColor) { // If piece is just an enemy piece
                        arrOfControlledSquares.add((sq.getOccupyingPiece()).getControlledSquares(this.getSquareArray(), sq)); // Get array of squares it controls and add it to arrOfControlledSquares variable
                    }
                }
            }
        }

        for (ArrayList<Square> arrSquares : arrOfControlledSquares) { // Loops through array for each array of controlled squares
            for (Square controlledSquare : arrSquares) { // Loops through the squares of the arrays
                if ((kingSquare != null) && (kingSquare == controlledSquare)) { // If kingSquare exists and it's a controlled square of an enemy piece king is in check
                    return true;
                }
            }
        }

        return false;
    }
}
