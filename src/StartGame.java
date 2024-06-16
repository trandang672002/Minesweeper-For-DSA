/* Name: Tran Dang ITITDK20001
 Purpose: Project for DSA 
*/

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
public class StartGame extends JFrame implements ActionListener {
    private int numRows;
    private int numCols;
    private int width;
    private int height;
    private String title;
    private MineSqu[][] map;

    ImageIcon imageMine = new ImageIcon("resources/bomb.png");
    
    int countClick = 0;
    boolean gameResult = false;  //stop the game when it true
    int numMines = 12;
    Random random = new Random();

    Container c = getContentPane();
    JPanel MainPanel = new JPanel();
    JPanel MineMap = new JPanel(new GridLayout());

    JButton Restart = new JButton("Restart");
    JButton Exit = new JButton("Exit");

   
    private ArrayList<MineSqu> mineLis = new ArrayList<MineSqu>();
    
    public StartGame(String title,int numRows, int numCols, int width, int height){
        this.numRows = numRows;
        this.numCols = numCols;
        this.width = width;
        this.height = height;
        this.title = title;

        setTitle(title);
       //setLocationRelativeTo();
        setSize(width,height+50);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("resources/bomb.png");
        setIconImage(icon.getImage());
        c.add(MainPanel = createMainPanel());
//---------------------------------------------------------------------------------------------------    
        //Button in control menu
        Restart.setBounds(10, 10, 40, 20);
        Restart.addActionListener(this);
        Restart.setFocusable(false);

        Exit.setBounds(60, 10, 40, 20);
        Exit.addActionListener(this);
        Exit.setFocusable(false);
        setVisible(true);
        
    }
//---------------------------------------------------------------------------------------------------   
    private void Mapgenerator(){
        MineSqu[][] map = new MineSqu[numRows][numCols];
        this.map=map;
        for(int x = 0;x < numRows;x++){
            for(int y = 0;y < numCols;y++){

                MineSqu SquButton = new MineSqu(x, y);
                map[x][y] = SquButton;
                SquButton.setFocusable(false);         
                SquButton.setFont(new java.awt.Font("Time New Roman", java.awt.Font.PLAIN, 40));
                SquButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent a){
                        if(gameResult){
                            return;
                        }
                        MineSqu SquButton = (MineSqu) a.getSource();

                        if (a.getButton() == MouseEvent.BUTTON1){
                            if (SquButton.getText() == ""){
                                if(mineLis.contains(SquButton)){
                                    revealMines();
                                }else{                                
                                    checkMine(SquButton.x, SquButton.y);
                                }
                            }
                        }else if(a.getButton() == MouseEvent.BUTTON3){
                            if(SquButton.getText() == "" && SquButton.isEnabled()){
                                SquButton.setText("X");
                            }else if(SquButton.getText() == "X"){
                                SquButton.setText("");
                            }
                        }
                    }
                });
                MineMap.add(SquButton);
                
            }
        }
        setMine();
    }
//---------------------------------------------------------------------------------------------------
    private void setMine(){
        mineLis = new ArrayList<MineSqu>();
        int minenow = numMines;
        while (minenow > 0){
            int x = random.nextInt(numRows);
            int y = random.nextInt(numCols);

            MineSqu SquButton = map[x][y];
            if(!mineLis.contains(SquButton)){
                mineLis.add(SquButton);
                minenow -=1;
            }
        }

    }
//---------------------------------------------------------------------------------------------------
    private void checkMine(int x, int y){
        if (x<0 || x >= numRows || y <0 || y >= numCols){
            return;
        }
        
        MineSqu a = map[x][y];
        if (!a.isEnabled()){
            return;
        }
        a.setEnabled(false);
        countClick +=1;
        
        int mineFound = 0;
        mineFound += countMine(x-1,y-1);
        mineFound += countMine(x-1, y);
        mineFound += countMine(x-1, y+1);

        mineFound += countMine(x, y-1);
        mineFound += countMine(x, y+1);

        mineFound += countMine(x+1, y-1);
        mineFound += countMine(x+1, y);
        mineFound += countMine(x+1, y+1);

        if(mineFound > 0){
            a.setText(Integer.toString(mineFound));
        }else{
            a.setText(" ");

            checkMine(x-1, y-1);
            checkMine(x-1, y);
            checkMine(x-1, y+1);

            checkMine(x, y-1);
            checkMine(x, y+1);

            checkMine(x+1, y-1);
            checkMine(x+1, y);
            checkMine(x+1, y+1);
        }

        if(countClick == numRows*numCols - mineLis.size()){
            gameResult = true;
            //add label for win
            int yesnopane = JOptionPane.showConfirmDialog(null,"You WIN, try again","You WIN, try again",JOptionPane.YES_NO_OPTION);
            System.out.println("WIN");
            if(yesnopane == 0){
                restartgame();
            }else if(yesnopane == 1 || yesnopane == 2 || yesnopane == -1){
                System.exit(0);
            }
        }
    }
//---------------------------------------------------------------------------------------------------
    private int countMine(int x, int y){

        if (x<0 || x >= numRows || y <0 || y >= numCols){
            return 0;
        }
        if(mineLis.contains(map[x][y])){
            return 1;
        }
        return 0;
    }
//---------------------------------------------------------------------------------------------------
    private void revealMines(){
        for (int i=0;i < mineLis.size();i++){
            MineSqu tile = mineLis.get(i);
            tile.setText("");
            tile.setIcon(imageMine);
            //tile.setText("B");
        }
        gameResult = true;
        int yesnopane = JOptionPane.showConfirmDialog(null,"You Lose, try again","You Lose, try again",JOptionPane.YES_NO_OPTION);
            if(yesnopane == 0){
                restartgame();
            }else if(yesnopane == 1 || yesnopane == 2 || yesnopane == -1){
                System.exit(0);
            }
        System.out.println("LOSE");
    }
//---------------------------------------------------------------------------------------------------  
    private JPanel createMainPanel() {
		//game panel
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createGraphicsPanel(), BorderLayout.CENTER);
		panel.add(createControlPanel(), BorderLayout.NORTH);
		return panel;
	}
//---------------------------------------------------------------------------------------------------  
    private JPanel createGraphicsPanel() {
		//design gameplay background
        Mapgenerator();   
		MineMap.setLayout(new GridLayout(numRows,numCols,1,1));
        MineMap.setPreferredSize(new Dimension(50, 50));
		MineMap.setBorder(new EmptyBorder(5, 5, 5, 5));
		return MineMap;
	}
//---------------------------------------------------------------------------------------------------  
    private JPanel createControlPanel() {
		//design button area
		JPanel panelControl = new JPanel();
		panelControl.setBorder(new EmptyBorder(10, 3, 5, 3));
		panelControl.add(Restart);
        panelControl.add(Exit);
        panelControl.add(setTextforControlPanel());
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(panelControl, BorderLayout.WEST);
		return panel;
	}

    private JLabel setTextforControlPanel(){
        JLabel labelText = new JLabel();
        labelText.setText("Mines: " + Integer.toString(numMines));
        return labelText;
    }
//---------------------------------------------------------------------------------------------------  
    private void restartgame(){
        MineMap.removeAll();
        countClick = 0;
        gameResult = false;      
		MainPanel.add(createGraphicsPanel(), BorderLayout.CENTER);
		MainPanel.validate();
		MainPanel.setVisible(true);
    }
//---------------------------------------------------------------------------------------------------     
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Restart) {
            restartgame();
        }
          
        if(e.getSource() == Exit){
              //exit game
              System.exit(0);
        }
    }
    
}
