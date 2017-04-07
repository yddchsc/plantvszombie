package com.dc.game;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;

@SuppressWarnings("all")

public class Frame
{
	//TODO:变量“声明”在此
	static long start = System.currentTimeMillis(); 
	FrameUtil 	fu 		= new FrameUtil();
	Plant 		plant 	= new Plant();
	Zombie 		zombie  = new Zombie();
	Card 		card 	= new Card();
	Constant 	con 	= new Constant();
	int index , hang,mx,my;
	boolean live=false;
	windows	ck	= null;
	settimes	ds1	= null;
	JTextArea	jta = new JTextArea();
	Frame()
	{
		card.addCard();
		
		//TODO:程序初始化在此
		ck = new windows();
		//windows 宽+2*立体边, 高+2*立体边+标题栏
		ck.setSize(5 * 2 + con.width, con.length + 2 * 5 + 25);
		//设定windows可见性setVisible  true/false
		ck.setVisible(true);
		ck.setResizable(false);
		//延时的毫秒
		ds1 = new settimes(100);
	}

	class windows extends JFrame
	{
		menus		cd		= null;
		panel		mb		= null;
		windowslistener	exit	= null;
		keylistener	jp		= null;
		
		JButton [] a = card.getJButtom();
		windows()
		{
			Font font = new Font("宋体",Font.BOLD,80);
			jta.setBounds(800, 0, 400, 100);
			jta.setEditable(false);
			jta.setFont(font);
			this.add(jta);
			
			
			for (int i = 0; i < a.length; i++) {
				this.add(a[i]);
			}
			
			
			jp = new keylistener();
			this.addKeyListener(jp);

			cd = new menus();
			this.setJMenuBar(cd);

			mb = new panel();
			this.add(mb);

			exit = new windowslistener();
			this.addWindowListener(exit);

			this.repaint();
		}

		class menus extends JMenuBar
		{
			JMenu		dan;	//menus
			JMenuItem	xiang1; //menus项

			menuslistener		cdjtq;

			menus()
			{
				
				dan = new JMenu("游戏"); //menus
				xiang1 = new JMenuItem("开局"); //menus项

				//this.add(dan);
				dan.add(xiang1);

				cdjtq = new menuslistener();
				
				for (int i = 0; i < a.length; i++) {
					a[i].addActionListener(cdjtq);
				}
			}

			class menuslistener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					//TODO:menus事件处理
					for (int i = 0; i < a.length; i++) {
						if (e.getSource() == a[i])
						{
							if(con.money >= card.car.get(i).getMoney()){
								int a = (int)(Math.random()*5+1);
								con.money -=card.car.get(i).getMoney()/2;
								if(card.getstyle(i) == 1)
									plant.addPeashooter(a);
								else if(card.getstyle(i) ==2)
									plant.addPea(a);
								else if(card.getstyle(i) == 3)
									plant.addThreepeator(a);
							}
						}
					}	
				}
			}
		}

		class windowslistener extends WindowAdapter
		{
			public void windowClosing(WindowEvent e)
			{
				ds1.xc.stop();
				System.exit(0);
			}
		}

		class keylistener implements KeyListener
		{
			//TODO:键盘处理，可加入KeyEvent.VK_XXX
			public void keyPressed(KeyEvent e)
			{
				switch (e.getKeyCode())
				{
				case KeyEvent.VK_LEFT://左
					
					break;
				case KeyEvent.VK_RIGHT://右
					
					break;
				case KeyEvent.VK_UP://上
				
					break;
				case KeyEvent.VK_DOWN://下
					
					break;
				}
				
			
				repaint();
			}

			public void keyReleased(KeyEvent e)
			{
			}

			public void keyTyped(KeyEvent e)
			{
			}
		}

		class panel extends JPanel
		{
			mouselistener	sb	= null;

			panel()
			{
				sb = new mouselistener();
				this.addMouseListener(sb);
				this.addMouseMotionListener(sb);
				
			}

			class mouselistener extends MouseAdapter implements MouseMotionListener{
				public void mousePressed(MouseEvent e){
					
				}

				public void mouseDragged(MouseEvent e){
				}

				public void mouseMoved(MouseEvent e){
				}

				public void mouseReleased(MouseEvent e){
				}

				public void mouseClicked(MouseEvent e)//mouse单击
				{
					//mx mymouse的位置
					 mx = e.getX();
					 my = e.getY();
					//mouse左键	BUTTON1、右键BUTTON3
					if (e.getButton() == MouseEvent.BUTTON1)
					{
						//TODO:mouse左键单击 
						 index = fu.setjp(mx, my,card);
						 live = true;
					}
					if (e.getButton() == MouseEvent.BUTTON3)
					{
						//TODO:mouse右键单击 
						 hang = fu.getjp(mx, my);
						 live = false;
					}
					repaint();
				}
				
			}

			public void paint(Graphics g)
			{
				//g.setColor(Color.BLUE);	//设定颜色：RED GREEN  BLUE  ORANGE  WHITE  GRAY BLACK PINK
				//g.drawRect(左,上,宽,高);	//矩形
				//g.fillRect(左,上,宽,高);	//实心矩形
				//g.drawOval(左,上,宽,高);	//椭圆
				//g.fillOval(左,上,宽,高);		//实心椭圆

		
				//Image  tu=(new ImageIcon("狐狸/5.png" )).getImage();	//路径：图片-右键-属性-路径src/	
				//g.drawImage(tu,-tux,0,null);						

				//TODO:绘图在此
				
					Image  tu=(new ImageIcon(getClass().getResource("/bg/background1.jpg"))).getImage();	//路径：图片-右键-属性-路径src/	
					g.drawImage(tu,0,100,null);	
			
					
					
					for (int i = 0; i < card.car.size(); i++) {
						card.car.get(i).draw(g);
					}
					for (int i = 0; i < zombie.gethead().size(); i++) {
						zombie.gethead().get(i).draw(g);
					} 
					for (int j = 0; j < plant.getpea().size(); j++) {
						plant.getpea().get(j).draw(g);
					} 
					
			}
		}
	}

	class settimes implements Runnable//实现Runnable接口
	{
		Thread	xc	= null;
		long	jianGe;

		settimes(long jianGe)
		{
			this.jianGe = jianGe;
			xc = new Thread(this);
			xc.start();
		}

		public void run()
		{
			while (true)
			{
				try
				{
					xc.sleep(jianGe);

					if (this == ds1)
					{
						//TODO:定时处理
					
						if(index !=card.car.size()  && hang !=0){
							plant.addwhich(index,hang);
							Constant.money -= card.car.get(index).money;
							index=card.car.size();
							hang =0;
						}
						fu.setdeffit(start, zombie);
						jta.setText("金币:"+con.money);
						card.setCardImages();
						zombie.ispeng(plant.getpea());
						
						for (int i = 0; i < plant.getpea().size(); i++) {
							plant.getpea().get(i).setImage();
							plant.getpea().get(i).walk();
						}
						for (int i = 0; i < zombie.gethead().size(); i++) {
							zombie.gethead().get(i).walk();
							zombie.gethead().get(i).setImage();
						}
						ck.repaint();
						
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	//main主方法 ，主类的“入口方法”
	public static void main(String[] args)
	{
		new Frame();
	}
}
