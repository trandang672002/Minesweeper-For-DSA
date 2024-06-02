package src;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuScreen extends JFrame implements ActionListener {
    JFrame screen = new JFrame("Minesweeper");
    String title = "Minesweeper";
    public int TitleSize = 70;
    public int numRows = 8;
    public int numCols = numRows;
    public int Width = numCols*TitleSize; //560
    public int Height = numRows*TitleSize;//560

    ImageIcon backgroundImage = new ImageIcon("resources/backgroudgame.jpg");

    JLabel titleLabel = new JLabel(backgroundImage);

    JPanel Menu = new JPanel(new BorderLayout());

    JButton startButton = new JButton("Start");
    JButton ExitButton = new JButton("Exit");

    public MenuScreen(){
//------JFrame----------------------------------------------------------------------------------------------------------------   
        screen.setSize(Width,Height+50);
      //  screen.setLocationRelativeTo(null);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setResizable(false);
        screen.setLayout(new BorderLayout());
        
//------ContentPane--------------------------------------------------------------------------------------------------------------------   
        Container c = screen.getContentPane();
        c.add(Menu);
//------Icon--------------------------------------------------------------------------------------------------------------------   
        ImageIcon icon = new ImageIcon("resources/bomb.png");
        screen.setIconImage(icon.getImage());
//------Background and Button in Label---------------------------------------------------------------------------------------------------------------------
        titleLabel.setBounds(0,0,Width,Height);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.add(startButton);
        titleLabel.add(ExitButton);

//------Menu Panel----------------------------------------------------------------------------------------------------------------------
        Menu.setBounds(0,0,Width,Height);
        Menu.add(titleLabel, BorderLayout.CENTER);
        
//-----JButton--------------------------------------------------------------------------------------------------------------------   
        startButton.setBounds(220, 250, 100, 50);
        startButton.addActionListener(this);
        startButton.setFocusable(false);

        ExitButton.setBounds(220,350,100,50);
        ExitButton.addActionListener(this);
        ExitButton.setFocusable(false);

        screen.setVisible(true);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getSource() == startButton) {
            //Start game
            new StartGame(title,numRows,numCols,Width,Height);
                screen.dispose();
                
        }
          
        if(e.getSource() == ExitButton){
              //exit game
              System.exit(0);
        }
          
    } 
}

