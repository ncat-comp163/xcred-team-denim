import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
public class TicTacToe extends JFrame implements ActionListener {
   /* Width of the game board. */
   private int width;
   /* Height of the game board. */
   private int height;
   /* 2D array of JButtons used to display 'X' or 'O' and to take action events. */
   private JButton[][] buttons;
   /* The current player number, either 1 or 2. */
   private int player;
   /* Going from "X" to "O" */
   double alternate = 0;
  
   public String winner;
   /* 2D array of JButtons that check whether the JButton needs to be cleared or not
    * for a new game. */
   private boolean[][] cells;

   /** 
    * Constructor for the TicTacToe board.
    * @param height: is the number of rows in the board
    * @param width: is the number of columns in the board
    */

   public TicTacToe(int height, int width) {
      this.width = width;
      this.height = height;
      cells = new boolean[height][width];
      buttons = new JButton[height][width];
      Container pane = getContentPane();
      setTitle("Let's play TicTacToe! Player 1 make your move.");
     // Set up the GridBagLayout then add buttons to the pane.
      setLayout(new GridBagLayout());
      setPreferredSize(new Dimension(500,500));
      Font font = new Font("SansSerif", Font.BOLD, 30);
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
            button.addActionListener(this);
            button.setFont(font);
         }
      }
     
      pack();
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
     /** 
      * Click once button for "X", click twice for "O".
      * @param: click ActionEvent for method. 
      */
   public void actionPerformed(ActionEvent click) {
      JButton button = (JButton)click.getSource();
      if (alternate%2==0) {
         button.setText("X");
         setTitle("Let's play TicTacToe! Player 1 make your move.");
      } else { 
         button.setText("O");
         setTitle("Let's play TicTacToe! Player 2 make your move.");

      }
      if (checkWin(buttons) == true) {
          int dialog;
          dialog = JOptionPane.showConfirmDialog(null, "Game Over! New Game?");
          if(dialog == 0) {
            resetButtons();
           } else if (dialog == 1 || dialog == 2) {
             super.dispose();
          }
      }
      alternate++;
        
     
   }
   /** 
    * After game is over hit resetButton to restart the game.
    */ 

   public void resetButtons() {
      for (int r = 0; r < height; r++) {
         for (int c = 0; c < width; c++) {  
            cells[r][c] = true;
            buttons[r][c].setText("");
            cells[r][c] = false;
         }
      }
   }
   public boolean isDraw() {
      for (int r = 0; r < height; r++) {
         for (int c = 0; c < width; c++) {  
           if (cells[r][c] = true) {
             int dialog;
             dialog = JOptionPane.showConfirmDialog(null, "Tie! New Game?");
             if(dialog == 0) {
               resetButtons();
             } else if (dialog == 1 || dialog == 2) {
               super.dispose();
             }
             return true;
         }
      }
   }
   return false;
   }

   /** 
    * Check different types of wins. Horizontal, Vertical, Diagonal. 
    * @param buttons array of where the button is. 
    * @return Ends game and prompt the user to start another game. 
    */ 
      
   public boolean checkWin(JButton[][] buttons) {
      boolean win;
         
      if(buttons[0][0].getText().equals((String)buttons[0][1].getText()) && buttons[0][1].getText().equals((String)buttons[0][2].getText())) {
         win = true;
         winner = (String)buttons[0][0].getText();
      
      } else if (buttons[1][0].getText().equals((String)buttons[1][1].getText()) && buttons[1][1].getText().equals((String)buttons[1][2].getText())) {
         win = true;
         winner = (String)buttons[1][0].getText();
      
      } else if(buttons[2][0].getText().equals((String)buttons[2][1].getText()) && buttons[2][1].getText().equals((String)buttons[2][2].getText())) {
         win = true;
         winner = (String)buttons[2][0].getText();
         
      } else if(buttons[0][0].getText().equals((String)buttons[1][0].getText()) && buttons[1][0].getText().equals((String)buttons[2][0].getText())) {
         win = true;
         winner = (String)buttons[0][0].getText();
         
      } else if(buttons[0][1].getText().equals((String)buttons[1][1].getText()) && buttons[1][1].getText().equals((String)buttons[2][1].getText())) {
         win = true;
         winner = (String)buttons[0][1].getText();
      
      } else if(buttons[0][2].getText().equals((String)buttons[1][2].getText()) && buttons[1][2].getText().equals((String)buttons[2][2].getText())) {
         win = true;
         winner = (String)buttons[0][2].getText();
      
      
      } else if(buttons[0][0].getText().equals((String)buttons[1][1].getText()) && buttons[1][1].getText().equals((String)buttons[2][2].getText())) {
         win = true;
         winner = (String)buttons[0][0].getText();
         
      } else if(buttons[0][2].getText().equals((String)buttons[1][1].getText()) && buttons[1][1].getText().equals((String)buttons[2][0].getText())) {
         win = true;
         winner = (String)buttons[0][2].getText();  
      
      } else {
         win = false;
         winner = "tie";
      }
      
      if(win == true && winner.equals("")) {
         win = false;
      }
      return win;
   } 
      
      
     /**
      * Invoke TicTacToe game from the main() method.
      * @param args tokens from the command line.
      */
   public static void main(String[] args) {
      TicTacToe game = new TicTacToe(3,3);
      
   }
}
