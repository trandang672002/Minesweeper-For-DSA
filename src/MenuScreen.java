package src;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.awt.font.*;

public class MenuScreen extends JFrame implements ActionListener {
    JFrame screen = new JFrame("Minesweeper");
    int TitleSize = 70;
    int numRows = 8;
    int numCols = numRows;
    int Width = numCols*TitleSize; //560
    int Height = numRows*TitleSize;//560

    ImageIcon backgroundImage = new ImageIcon("resources/backgroudgame.jpg");

    JLabel titleLabel = new JLabel(backgroundImage);

    JPanel Menu = new JPanel(new BorderLayout());

    JButton startButton = new JButton("Start");
    JButton ExitButton = new JButton("Exit");

    public MenuScreen(){
//------JFrame----------------------------------------------------------------------------------------------------------------   
        screen.setSize(Width,Height);
        screen.setLocationRelativeTo(null);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setResizable(false);
        screen.setVisible(true);
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
        // titleLabel.setText("Welcome to Minesweeper");
        // titleLabel.setFont(new java.awt.Font("Time New Roman", java.awt.Font.BOLD, 25));
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
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getSource() == startButton) {
            //Start game
            Menu.setVisible(false);

          }
          
          if(e.getSource() == ExitButton){
              //exit game
              System.exit(0);
          }
          
    } 
}

