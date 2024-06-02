package src;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.scene.paint.Color;

public class StartGame extends JFrame implements ActionListener {
    private int numRows;
    private int numCols;
    private int width;
    private int height;
    private String title;
    private MineSqu[][] map;


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
      //setLocationRelativeTo(null);
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

                // SquButton.setFocusable(false);
                // SquButton.setMargin(new Insets(0,12,0,12));
                // SquButton.setText("1");
                SquButton.setFont(new java.awt.Font("Time New Roman", java.awt.Font.PLAIN, 40));
                SquButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent a){
                        MineSqu SquButton = (MineSqu) a.getSource();
                        
                        if (a.getButton() == MouseEvent.BUTTON1){
                            if (SquButton.getText() == ""){
                                if(mineLis.contains(SquButton)){
                                    revealMines();
                                }else{                                
                                    checkMine(SquButton.x, SquButton.y);
                                }
                            }
                        }
                    }
                });
                MineMap.add(SquButton);
                
            }
        }
        //test bomb
        setMine(2, 2);//test bomb
    }

    private void setMine( int x, int y){//test bomb

        mineLis = new ArrayList<MineSqu>();//test bomb

        mineLis.add(map[2][2]);//test bomb
        mineLis.add(map[7][2]);
        mineLis.add(map[5][2]);
        mineLis.add(map[2][7]);
        mineLis.add(map[2][3]);
        mineLis.add(map[3][3]);
        mineLis.add(map[4][4]);
        mineLis.add(map[7][7]);

    }//test bomb
    //test bomb

    private void checkMine(int x, int y){
        if (x<0 || x >= numRows || y <0 || y >= numCols){
            return;
        }
        
        MineSqu a = map[x][y];
        if (!a.isEnabled()){
            return;
        }
        a.setEnabled(false);
        
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
            // if(mineFound == 1){
            //     a.setTextColor(java.awt.Color.BLUE);
            // }else if(mineFound == 2){
            //     a.setTextColor(java.awt.Color.GREEN);
            // }else if(mineFound == 3){
            //     a.setTextColor(java.awt.Color.RED);
            // }else if(mineFound == 4){
            //     a.setTextColor(java.awt.Color.PINK);
            // }else if(mineFound == 5){
            //     a.setTextColor(java.awt.Color.YELLOW);
            // }else if(mineFound == 6){
            //     a.setTextColor(java.awt.Color.ORANGE);
            // }
        }else{
            a.setText("");

            checkMine(x-1, y-1);
            checkMine(x-1, y);
            checkMine(x-1, y+1);

            checkMine(x, y-1);
            checkMine(x, y+1);

            checkMine(x+1, y-1);
            checkMine(x+1, y);
            checkMine(x+1, y+1);
        }


    }

    private int countMine(int x, int y){

        if (x<0 || x >= numRows || y <0 || y >= numCols){
            return 0;
        }
        if(mineLis.contains(map[x][y])){
            return 1;
        }
        return 0;
    }

    private void revealMines(){
        for (int i=0;i < mineLis.size();i++){
            MineSqu tile = mineLis.get(i);
            tile.setText("B");
        }
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

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(panelControl, BorderLayout.WEST);
		return panel;
	}
//---------------------------------------------------------------------------------------------------  
    private void restartgame(){
        MineMap.removeAll();
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
