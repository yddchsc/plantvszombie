package com.dc.game;

import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.swing.*;

public class Start
{
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
	BombMusic music = null;
	JTextArea	jta = new JTextArea();
	Start()
	{
		card.addCard();
   		
		ck = new windows();
		ck.setSize(5 * 2 + con.width, con.length + 2 * 5 + 25);
		ck.setVisible(true);
		ck.setResizable(false);

		ds1 = new settimes(100);
		music = new BombMusic();
	}

	class windows extends JFrame
	{
		menus cd = null;
		faceboard mb = null;
		windowslistener	exit = null;
		keylistener	jp = null;
		
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

			mb = new faceboard();
			this.add(mb);

			exit = new windowslistener();
			this.addWindowListener(exit);

			this.repaint();
		}

		class menus extends JMenuBar
		{
			JMenu		dan;//菜单
			JMenuItem	xiang1; //菜单项

			menuslistener		cdjtq;

			menus()
			{
				
				dan = new JMenu("娓告垙"); //menus
				xiang1 = new JMenuItem("寮�眬"); //menus椤�

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
					//TODO:menus浜嬩欢澶勭悊
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
				music.xc.stop();
				System.exit(0);
			}
		}

		class keylistener implements KeyListener
		{
			//TODO:閿洏澶勭悊锛屽彲鍔犲叆KeyEvent.VK_XXX
			public void keyPressed(KeyEvent e)
			{
				switch (e.getKeyCode())
				{
				case KeyEvent.VK_LEFT://宸�
					
					break;
				case KeyEvent.VK_RIGHT://鍙�
					
					break;
				case KeyEvent.VK_UP://涓�
				
					break;
				case KeyEvent.VK_DOWN://涓�
					
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

		class faceboard extends JPanel
		{
			mouselistener	sb	= null;

			faceboard()
			{
				sb = new mouselistener();
				this.addMouseListener(sb);
				this.addMouseMotionListener(sb);
				
			}

			class mouselistener extends MouseAdapter implements MouseMotionListener
			{
				public void mousePressed(MouseEvent e)
				{
					
				}

				public void mouseDragged(MouseEvent e)
				{
				}

				public void mouseMoved(MouseEvent e)
				{
				}

				public void mouseReleased(MouseEvent e)
				{
				}

				public void mouseClicked(MouseEvent e)//mouse鍗曞嚮
				{
					//mx mymouse鐨勪綅缃�
					 mx = e.getX();
					 my = e.getY();
					//mouse宸﹂敭	BUTTON1銆佸彸閿瓸UTTON3
					if (e.getButton() == MouseEvent.BUTTON1)
					{
						//TODO:mouse宸﹂敭鍗曞嚮 
						 index = fu.setjp(mx, my,card);
						 live = true;
					}
					if (e.getButton() == MouseEvent.BUTTON3)
					{
						//TODO:mouse宸﹂敭鍗曞嚮 
						 hang = fu.getjp(mx, my);
						 live = false;
					}
					repaint();
				}
				
			}

			public void paint(Graphics g){
				
					Image  tu=(new ImageIcon(getClass().getResource("/bg/background1.jpg"))).getImage();
					g.drawImage(tu,0,100,null);		
					tu=(new ImageIcon(getClass().getResource("/SeedBank.png"))).getImage();
					g.drawImage(tu,0,0,null);
					
					
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
	/**
 	*该方法为音频流缓冲方法,在调用音频播放线程时调用
 	*//*
 	public BufferedInputStream loadBomb(){
 		BufferedInputStream bufbomb = null;
 	try{ 
 		File dir = new File("E:/plantvszombie/bin/"); //所要播放文件的路径 
 		File fObject = new File(dir,"faster.wav"); //音频名 
 		FileInputStream file = new FileInputStream(fObject); 
 		bufbomb = new BufferedInputStream(file); 
 	}catch(IOException e) {
 		System.out.println(e.getMessage());
 	} 
 		return bufbomb;
 	}*/

	private class BombMusic implements Runnable{ //通过流读取声音文件
 		//BufferedInputStream buf;
		Thread	xc	= null;

		BombMusic()
		{
			xc = new Thread(this);
			xc.start();
		}
 		public void run(){
 			/*buf = loadBomb();
 			try{
 				AudioInputStream audio = new AudioInputStream(buf); 
 				AudioPlayer.player.start(audio); 
 			}catch(IOException e){
 				e.printStackTrace();
 			}*/
 			PlayMusic sound =new PlayMusic("faster.wav");
 	        InputStream stream =new ByteArrayInputStream(sound.getSamples());
 	        // play the sound
 	        sound.play(stream);
 		}
 	}

	class settimes implements Runnable
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

	public static void main(String[] args)
	{
		new Start();
	}
}
