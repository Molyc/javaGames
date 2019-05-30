package Role;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JPanel;

/** 
 * ������ 
 * @author cxt 
 */
public class Monster extends JPanel implements Runnable
{
	private int MonsterID;//���޵�ID
	private int zhuangtai=0;//���޵�״̬ �����������ͼ
	private int HP;//����Ѫ��
	public boolean beAtt = false; //�Ƿ񱻹���
	public boolean aLive = true;	//�Ƿ���
	//�����ڻ����е�λ��
	int x= 50;
	int y= 500; 
	Random r=new Random();//�����������������ɹ��޵ĳ�����
	//���캯��
	public Monster(int id) {
		MonsterID = id;
		HP = 2000;
		x = 500 ;
		y = r.nextInt(200); //�������
	}
	
	public boolean isBeAtt() {
		return beAtt;
	}
	public void setBeAtt(boolean beAtt) {
		this.beAtt = beAtt;
	}
	public int getID()
	{
		return MonsterID;
	}
	public Monster(int id,int x,int y)
	{
		MonsterID=id;
		switch(MonsterID)
		{
		case 0:
		{
			HP=2000;
			break;
		}
		case 1:
		{
			HP=5000;
			break;
		}
		}
	}
	public int getHP()
	{
		return HP;
	}
	public void setzhuangtai(int m)
	{
		zhuangtai=m;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	//���ޱ����� Ѫ������
	public void beAtt(int SNH)
	{
		HP -= SNH;
		if( HP <= 0 )
			{
			setzhuangtai(3);
			aLive = false;
			repaint();
			}
	}
	
	//���޵Ļ滭�߳�
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if(MonsterID == 0)	//��ͬID�Ĺ�����ͼ��ͬ
		switch(zhuangtai)  //ͨ�����޵�״̬���ı���޵���ͼ
		{
		case 0:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/�l�̿�/վ��/��.gif"),x,y,this);break;
		case 1:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/�l�̿�/�ƶ�/��.gif"),x,y,this);break;
		case 2:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/�l�̿�/������/��.png"),x,y,this);break;
		case 3:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/Night_grave_graphic.png"),x,y,this);break;
		}else {
			switch (zhuangtai) {
			case 0:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/��ӡʯͷ��/վ��/��.gif"),x,y,this);break;
			case 1:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/��ӡʯͷ��/�ƶ�/��.gif"),x,y,this);break;
			case 2:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/��ӡʯͷ��/������/��.png"),x,y,this);break;
			case 3:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/Night_grave_graphic.png"),x,y,this);break;
			}
		
		}
	
	}
	//���ޱ��������޸�״̬
	public synchronized void BeAttack() {
		setzhuangtai(2); //�յ�����״̬
			repaint();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			setBeAtt(false);
			setzhuangtai(1);//����״̬
		}
	
	@Override
	public synchronized void run() {
		while(x > 0 && aLive) {
			setzhuangtai(1);
			repaint();
			if(beAtt)  //������ֹͣǰ��
				BeAttack();
			x = x - 1;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(x <= 300)
			System.out.println("��Ϸ����");
		// TODO Auto-generated method stub
	}	
}

