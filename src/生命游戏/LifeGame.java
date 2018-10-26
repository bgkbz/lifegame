package 生命游戏;

import javax.swing.*;
import java.awt.*;

public class LifeGame extends JFrame{  
  
      
    private static final long serialVersionUID = 1L;
  
    LifeGame(){  
        this.setSize(860,830);
        this.setTitle("生命游戏");
        this.add(new WorldMap());  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setLocationRelativeTo(null);  
        this.setResizable(false);
        Dimension displaySize=Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize=this.getSize();
        if (frameSize.width > displaySize.width)
            frameSize.width = displaySize.width;// 窗口的宽度不能大于显示器的宽度
        this.setLocation((displaySize.width-frameSize.width)/2,(displaySize.height-frameSize.height)/2);
        this.setVisible(true);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Image/icon.png"));
        this.setIconImage(imageIcon.getImage());


    }  
      
    public static void main(String[] args){  
        LifeGame game = new LifeGame();  
        game.setVisible(true);  
    }  
  
}
