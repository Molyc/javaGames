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
 * ��ɫ�� 
 * @author cxt 
 * 
 */  
public class Player extends JPanel implements Runnable{  
    
    static int x = 0;  
    static int y = 0;  
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

	//��ɫ�Ĳ���  
    static int step = 10;  
    //��ɫ�Ƿ��ƶ�  
    public static boolean up = false;  
    public static boolean down = false;  
    public static boolean left = false;  
    public static boolean right = false;  
    //�����״̬ Ĭ��Ϊ2
    static int status = 2 ;
    
    public int getSNH() {
		return SNH;
	}
	public void setSNH(int sNH) {
		SNH = sNH;
	}

	int SNH = 500 ; //����Ĺ�����
       

   public void setStatus(int a)//����״̬
   {
       status=a;
   }
   
   //�滭����
   @Override
protected void paintComponent(Graphics g) {
	// TODO Auto-generated method stub
	super.paintComponent(g);
	switch (status) {
	case 0:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/����/��·/������.gif"),x,y,this);break;
	case 1:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/����/��·/������.gif"),x,y,this);break;
	case 2:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/����/վ��/�泯��վ.gif"),x,y-15,this);break;
	case 3:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/����/վ��/�泯��վ.gif"),x,y-15,this);break;
	case 4:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/����/����/�泯�ҹ�����ʽ1.gif"),x-25,y-25,this);break;
	case 5:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/����/����/�泯�󹥻���ʽ1.gif"),x-25,y-25,this);break;
	
	}

   }
    /** 
     * ��ɫ�ƶ��ķ��� 
     */  
   @Override
public void run() {
	// TODO Auto-generated method stub
	   move(); //�����ƶ�
	   this.repaint(); //�ػ�����
       try {
        Thread.sleep(1000); //�߳�����
      } catch (InterruptedException e) {
        e.printStackTrace();
       }
}
   
    public void move(){ 
    	if(x>0 && x<1340 ) //�߽��ж�
    	{
        if(up){  
            //�ı��ɫ�ڵ�ͼ�е�λ��  
            y=y-step;  
        }  
        if(down){  
            y=y+step;  
        }  
        if(left){  
            x=x-step;  
        }  
        if(right){  
            x=x+step;  
        }  
       this.repaint();
    	}
    	else if(x<=0)//�߽�
        	x++;
        else if(x>=1340)//�߽�
        	x--;
    }  
    
}  