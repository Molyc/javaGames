package Role;


import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


/** 
 * �㶹�� 
 * @author cxt 
 * 
 */  
public class Pea extends JPanel implements Runnable{  
	
    
	 //�㶹�����ڻ����е�λ��
    static int x = 130;  
    static int y = 100;  
    //�ӵ���λ��
    public static int mx = 150;
    public static int my = y;
	//��ɫ�Ĳ���  
    static int step = 10;  
    //��ɫ�Ƿ��ƶ�  
    public static boolean up = false;  
    public static boolean down = false;  
    public static boolean left = false;  
    public static boolean right = false;  
	int SNH = 500 ; // �㶹�Ĺ�����
	
    public int getMx() {
		return mx;
	}
	public int getMy() {
		return my;
	}
	public int getX() {
		return x;
	}
	public static void setX(int x) {
		Player.x = x;
	}
	public int getY() {
		return y;
	}
	public static void setY(int y) {
		Player.y = y;
	}


    public int getSNH() {
		return SNH;
	}
	public void setSNH(int sNH) {
		SNH = sNH;
	}

	//�滭���� ����ֲ���Լ��ӵ���λ��
   @Override
protected void paintComponent(Graphics g) {
	// TODO Auto-generated method stub
	super.paintComponent(g);
	g.drawImage(Toolkit.getDefaultToolkit().getImage("img/sheshou.gif"),x,y,this);
	g.drawImage(Toolkit.getDefaultToolkit().getImage("img/zidan.png"),mx,y,this);
	
   }
    /** 
     * ��ɫ�ƶ��ķ��� 
     */  
   @Override
public void run() {
	// TODO Auto-generated method stub
	   while(true) {
		   mx = mx + 6;		//�ӵ�ǰ��
		   this.repaint(); //�ػ�����
       try {
        Thread.sleep(80); //�߳�����
      } catch (InterruptedException e) {
        e.printStackTrace();
       }
	  }
}
   
    public void move(){ 
    	if(x>0 && x<1340 ) //�߽��ж�
    	{
        if(up){  
            y=y-step; //�����ƶ�  
        }  
        if(down){  		
            y=y+step;  //�����ƶ�
        }
        this.repaint();
    	}
    	else if(x<=0)
        	x++;
        else if(x>=1340)
        	x--;
    }  
    
}  