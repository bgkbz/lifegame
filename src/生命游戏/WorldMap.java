package 生命游戏;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

  
public class WorldMap extends JPanel {  
  
    private static final long serialVersionUID = 2L;
  
    private final int width = 33;
  
    private final int height = 33;
  
    private final char WORLD_MAP_NONE = 'N';  
  
    private final char WORLD_MAP_ALIVE = 'A';

    private  char[][] world=new char[24][26];


    public  void drawHeart(){
        int row=world.length;
        int volumn=world[0].length;
        for (int i=0;i<row;i++){
            for (int j=0;j<volumn;j++)
                world[i][j]='N';
        }
        int n=3;
        for(int j = 7; j <= 9; j++)
            world[n][j] = world[n][j+9] = 'A';
        n++;
        for(int j=6;j<=10;j++)
            world[n][j]=world[n][j+9]='A';
        n++;
        for(int j=5;j<=11;j++)
            world[n][j]=world[n][j+9]='A';
        n++;
        for(int j=4;j<=12;j++)
            world[n][j]=world[n][j+9]='A';
        n++;
        for(int j=3;j<=13;j++)
            world[n][j]=world[n][j+9]='A';
        n++;
        for(int j=2;j<=14;j++)
            world[n][j]=world[n][j+9]='A';
        n++;
        for(int j=1;j<=15;j++)
            world[n][j]=world[n][j+9]='A';
        n++;
        for(int j=2;j<=23;j++)
            world[n][j]='A';
        n++;
        for(int j=3;j<=22;j++)
            world[n][j]='A';
        n++;
        for(int j=4;j<=21;j++)
            world[n][j]='A';
        n++;
        for(int j=5;j<=20;j++)
            world[n][j]='A';
        n++;
        for(int j=6;j<=19;j++)
            world[n][j]='A';
        n++;
        for(int j=7;j<=18;j++)
            world[n][j]='A';
        n++;
        for(int j=8;j<=17;j++)
            world[n][j]='A';
        n++;
        for(int j=9;j<=16;j++)
            world[n][j]='A';
        n++;
        for(int j=10;j<=15;j++)
            world[n][j]='A';
        n++;
        for(int j=11;j<=14;j++)
            world[n][j]='A';
        n++;
        for(int j=12;j<=13;j++)
            world[n][j]='A';

    }

  
    private final char[][] world2 = {  
            { 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N' },  
            { 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N' },  
            { 'N', 'N', 'A', 'N', 'N', 'N', 'N', 'N' },  
            { 'N', 'A', 'A', 'A', 'N', 'N', 'N', 'N' },  
            { 'N', 'N', 'A', 'N', 'N', 'N', 'N', 'N' },  
            { 'N', 'N', 'A', 'N', 'N', 'N', 'N', 'N' },  
            { 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N' },  
            { 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N' },  
            { 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N' }  
  
    };  
  
   // private Image none = LifeGameImages.NONE.getImage();  
  // private Image alive = LifeGameImages.ALIVE.getImage();  
  
    /** 
     * 当前细胞下一状态 
     */  
    private char[][] nextStatus = new char[world.length][world[0].length];  
  
    private char[][] tempStatus = new char[world.length][world[0].length];  
  
    private Timer timer;  
  
    // 动画帧之间的延时，每秒60帧  
    private final int DELAY_TIME = 1200;
  
    public WorldMap() {
        this.drawHeart();
        this.startAnimation();
        this.setBackground(Color.gray);

    }  
  
    /** 
     * 画图形界面 
     *  
     */  
    @Override  
    protected void paintComponent(Graphics g) {  
        super.paintComponent(g);
        g.setColor(Color.pink);
        for (int i = 0; i < nextStatus.length; i++) {  
            for (int j = 0; j < nextStatus[i].length; j++) {  
                if (nextStatus[i][j] == WORLD_MAP_ALIVE) {  
                    g.fillRect( j * width, i * height, width, height);
                   
                } else {  
                	 g.drawRect( j * width, i * height, width, height);
                }  
            }  
        }  
    }  
  
    /** 
     * 改变细胞状态 
     *  
        */  
    private void changeCellStatus() {  
        for (int row = 0; row < nextStatus.length; row++) {  
            for (int col = 0; col < nextStatus[row].length; col++) {  
  
                switch (neighborsCount(row, col)) {  
                case 0:  
                case 1:  
                case 4:  
                case 5:  
                case 6:  
                case 7:  
                case 8:  
                    nextStatus[row][col] = WORLD_MAP_NONE;  
                    break;  
                case 2:  
                    nextStatus[row][col] = tempStatus[row][col];  
                    break;  
                case 3:  
                    nextStatus[row][col] = WORLD_MAP_ALIVE;  
                    break;  
                }  
            }  
        }  
        copyWorldMap();  
    }  
  
    /** 
     * 获取当前坐标点临近细胞个数 
     *  
         */  
    private int neighborsCount(int row, int col) {  
        int count = 0, r = 0, c = 0;  
  
        for (r = row - 1; r <= row + 1; r++) {  
            for (c = col - 1; c <= col + 1; c++) {  
                if (r < 0 || r >= tempStatus.length || c < 0  
                        || c >= tempStatus[0].length) {  
                    continue;  
                }  
                if (tempStatus[r][c] == WORLD_MAP_ALIVE) {  
                    count++;  
                }  
            }  
        }  
        if (tempStatus[row][col] == WORLD_MAP_ALIVE) {  
            count--;  
        }  
        return count;  
    }  
  
    /** 
     * 开始动画 
     *  
         */  
    private void startAnimation() {  
        for (int row = 0; row < world.length; row++) {  
            for (int col = 0; col < world[0].length; col++) {  
                nextStatus[row][col] = world[row][col];  
                tempStatus[row][col] = world[row][col];  
            }  
        }  
        // 创建计时器  
        timer = new Timer(DELAY_TIME, new ActionListener() {
  
            @Override  
            public void actionPerformed(ActionEvent e) {
                changeCellStatus();  
                repaint();  
            }  
        });  
        // 开启计时器  
        timer.start();  
    }  
  
    /** 
     * 复制地图 
     *  
 
     */  
    private void copyWorldMap() {  
        for (int row = 0; row < nextStatus.length; row++) {  
            for (int col = 0; col < nextStatus[row].length; col++) {  
                tempStatus[row][col] = nextStatus[row][col];  
            }  
        }  
    }  
}  