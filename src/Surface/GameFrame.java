package Surface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.omg.CORBA.PUBLIC_MEMBER;

import Role.Monster;
import Role.Pea;
import Role.Player;

import javax.crypto.NullCipher;
import javax.security.auth.x500.X500Principal;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.JLabel;
import java.awt.Font;

//����
public class GameFrame {
	/*
	 * 1�������߳�
	 * 2�������߳�
	 * 3���������߳�
	 * 4���ӵ������߳�
	 */
	private boolean over = false; //��Ϸ������־
	private JFrame frame;
	private Random random = new Random();
	private int attacking=0;
	// ����һ������Ϊ12���̳߳�
	ExecutorService executorService = Executors.newFixedThreadPool(12);  
	/**���﷽��״̬
	 * 0=��
	 * 1=��
	 * */
	private int Direction; 
	//��ǰ�������� 1Ϊսʿ 2Ϊ����
	private int role = 2;
	//��ס������JAVA���Ƕ��󣡣���
	//��������
	Monster[] monster = new Monster[10];
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {  //ˢ��ͼ��
			public void run() {
				try {
					GameFrame window = new GameFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameFrame() {
		initialize();
	}

	Player player = new Player();	
	Pea pea = new Pea();
	JLabel label = new JLabel("\u6E38\u620F\u7ED3\u675F");
	
	class Attack implements Runnable	//�������߳�
	 {
		 @Override
		public void run() {
			 if(attacking == 0)//�ж����ﳯ��
			 {
				 attacking = 1; //��ֻ֤��һ�������߳�
				 for (int i = 0; i < monster.length; i++) {  //���й����ж��Ƿ񱻹���
					 if(Direction == 1)
					{
						 if(monster[i].getY() <= player.getY()+10 &&
								 monster[i].getY() >= player.getY()-10 &&	 
								 (monster[i].getX() -8) <= (player.getX()+100) && 
								 monster[i].getX() > player.getX() )   //ͨ�����޵�λ���ж��Ƿ񱻹���
						 	{
								if(monster[i].aLive)  //������
						 		{
							 		monster[i].setBeAtt(true); //��Ѫ
							 		monster[i].setzhuangtai(2); //�µ�״̬
							 		monster[i].beAtt(player.getSNH()); 
						 		}
								else {
									monster[i].setzhuangtai(3);
									boolean notOver = true; 
									for(int j = 0 ; j<monster.length ;j++)
										{
										if(monster[j].aLive )
											notOver = false; 
										}
										if(notOver)
											player.add(label);  //ȫ��������Ϸ���� 
								}
								}
					}
				}
				 try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 	// TODO Auto-generated method stub
				 if(Direction == 1)
					 player.setStatus(2); //����
					else 
					 player.setStatus(3); //����
				 attacking = 0;
			 }
			 
		 }
	 }
	class PeaAtt implements Runnable		//�㶹�������߳�
	 {
		 @Override
		public synchronized void run() {
			 while (true) {
				 for (int i = 0; i < monster.length; i++) { //ÿ�������ж��Ƿ񱻹���
					 if(monster[i].getY() <= pea.getY() + 10 &&
								 monster[i].getY() >= pea.getY()-10 &&	 
								 (monster[i].getX() -8) <= (pea.getMx() - monster[i].getX() + 100) )
						 	{
						 		
								if(monster[i].aLive)
						 		{
							 		monster[i].setBeAtt(true);
							 		monster[i].setzhuangtai(2);
							 		monster[i].beAtt(pea.getSNH());
							 		pea.mx = 130;
						 		}
								else 
									monster[i].setzhuangtai(3);
						 	}
					}
				 if (pea.getMx() >= 1000)
					 pea.mx = 130;
				 try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		// TODO Auto-generated method stub
			
	 }	
	 }
	
	private void initialize() {
		frame = new JFrame();
		frame.addKeyListener(new KeyAdapter() { //���ü�����
			public void keyPressed(KeyEvent e) {
						
			            int code = e.getKeyCode();  
			            switch (code) {  
			            case KeyEvent.VK_UP:  
			                if(role == 1)
			            	Player.up = true;
			                else 
							Pea.up = true;	
			                break;  
			            case KeyEvent.VK_DOWN:  
			                if(role == 1)
			            	Player.down = true;
			                else 
			                Pea.down = true;
			                break;  
			            case KeyEvent.VK_LEFT:  
			            		Player.left = true;  
			            		player.setStatus(1);  //����
			            		Direction = 0;
			                break;  
			            case KeyEvent.VK_RIGHT:
			            	Player.right = true;  
			                player.setStatus(0); //����
			                Direction = 1;
			                break;  
			            case KeyEvent.VK_X:  //���﹥��
			            	executorService.execute(new Thread(new Attack())); //��ӹ����߳�
							if(Direction == 1)
							 player.setStatus(4); //����
							else 
							 player.setStatus(5); //����
			            	break;
			            case KeyEvent.VK_Z:
			            	if(role == 1)
			            		role = 2;
			            	else 
								role = 1;
			            default:  
			                break;  
			            }  
			            if(role == 1)
			            player.move();
			            else {
			            	 pea.move();
						}
			        }  
			        //�������ͷ�  
			        public void keyReleased(KeyEvent e){  
			            int code = e.getKeyCode();  
			            switch (code) {  
			            case KeyEvent.VK_UP:  
			            	Player.up = false;  
			            	Pea.up = false;  
			            	break;  
			            case KeyEvent.VK_DOWN:  
			                Player.down = false;  
			                Pea.down = false;
			                break;  
			            case KeyEvent.VK_LEFT:  
			                Player.left = false; 
			                player.setStatus(3); //����
			                break;  
			            case KeyEvent.VK_RIGHT:  
			                Player.right = false;  
			                player.setStatus(2); //����
			                break;  
			            default:  
			                break;  
			            }
			            player.move();
			        }  
		});
		frame.setBounds(400, 100, 1200, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		player.setBounds(50, 320, 1500, 900);
		frame.getContentPane().add(player);
		player.setBackground(null);
		player.setLayout(null);
		player.setOpaque(false); //����͸�� ��paint() �й� ������дpaint() 
		
		
			
		label.setBounds(513, 88, 412, 193);
		label.setFont(new Font("����", Font.PLAIN, 96));

		//�����㶹
		pea.setBounds(400, 100, 1200, 650);
		frame.getContentPane().add(pea);
		pea.setBackground(null);
		pea.setLayout(null);
		pea.setOpaque(false); //����͸�� ��paint() �й� ������дpaint() 
		executorService.execute(new Thread(new Pea()));
	
		//�������
		for (int i = 0; i < 10; i++) {
			monster[i] = new Monster(random.nextInt(2));
			monster[i].setBounds(monster[i].getX(), monster[i].getY(), 1500, 900);
			frame.getContentPane().add(monster[i]);
			monster[i].setBackground(null);
			monster[i].setLayout(null);
			monster[i].setOpaque(false); //����͸�� ��paint() �й� ������дpaint() 
			executorService.execute(new Thread(monster[i]));
		}
		
		//�㶹���
		new Thread(new PeaAtt()).start();
		GamePanel panel = new GamePanel();
		panel.setBounds(0, 0, 1500, 900);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		 new Thread(player).start();
		
	}
}
