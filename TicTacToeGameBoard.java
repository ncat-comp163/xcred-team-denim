import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Class to implement TicTacToe.
 * Two players take turns placing their 'X' or 'O' character
 * in one of 9 open squares.
 * The player to have three in a row of their character first
 * wins the game.
 * @author tbell1@aggies.ncat.edu
 * @author tbbryant@aggies.ncat.edu
 * @author gcspence@aggies.ncat.edu
 */
 public class TicTacToe extends JFrame /*implements ActionListener*/ {
   /* Width of the game board. */
   private int width;
   /* Height of the game board. */
   private int height;
   /* 2D array of JButtons used to display 'X' or 'O' and to take action events. */
   private JButton[][] buttons;
   /* The current player number, either 1 or 2. */
   private int player;
  
   /** 
    * Constructor for the TicTacToe board.
    * @param height: is the number of rows in the board
    * @param width: is the number of columns in the board
    */
   
   public TicTacToe(int height, int width) {
     this.width = width;
     this.height = height;
     buttons = new JButton[height][width];
     setTitle("Let's play TicTacToe! Player 1 make your move.");
     // Set up the GridBagLayout then add buttons to the pane.
     Container pane = getContentPane();
     setLayout(new GridBagLayout());
     setPreferredSize(new Dimension(500,500));
     GridBagConstraints gbc = new GridBagConstraints();
     gbc.weightx = gbc.weighty = 1.0;
     gbc.fill = GridBagConstraints.BOTH;
     for (int r = 0; r < height; r++) {
         for (int c = 0; c < width; c++) {
            JButton button = new JButton();
            gbc.gridx = c;
            gbc.gridy = r;
            pane.add(button, gbc);
            
            buttons[r][c] = button;
            
            //button.addActionListener(this);
          }
     }
     pack();
     setVisible(true);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }
     /**
      * Invoke from the main() method.
      * @param args tokens from the command line.
      */
     public static void main(String[] args) {
       TicTacToe game = new TicTacToe(3,3);
   }
}

   


