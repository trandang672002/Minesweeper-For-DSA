package src;

import java.awt.Color;

import javax.swing.JButton;

public class MineSqu extends JButton{

    int x;
    int y;
    public MineSqu(int x, int y){
        this.x=x;
        this.y=y;
        
    }

    public void setTextColor(Color color){
        setForeground(color);
    }

}
