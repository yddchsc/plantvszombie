package com.dc.game;

import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.swing.*;

public class Start{
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
	Start(){
		card.addCard();
   		
		ck = new windows();
		ck.setSize(5 * 2 + con.width, con.length + 2 * 5 + 25);
		ck.setVisible(true);
		ck.setResizable(false);

		ds1 = new settimes(100);
		music = new BombMusic();
	}

	class windows extends JFrame{
		//menus cd = null;
		panel mb = null;
		windowslistener	exit = null;

		windows(){
			Font font = new Font("宋体",Font.BOLD,80);
			jta.setBounds(800, 0, 400, 100);
			jta.setEditable(false);
			jta.setFont(font);
			this.add(jta);

			mb = new panel();
			this.add(mb);

			exit = new windowslistener();
			this.addWindowListener(exit);

			this.repaint();
		}

		class windowslistener extends WindowAdapter{
			public void windowClosing(WindowEvent e){
				ds1.xc.stop();
				music.xc.stop();
				System.exit(0);
			}
		}
		class panel extends JPanel{
			static final int frame_width = 71;
			static final int frame_height = 71;

			PicPanel pic = null;
			private int pic_x;
			private int pic_y;
			
			//前一个位置
			int begin_x = 0;
			int begin_y = 0;

			int i = 0;
			
			boolean inThePic = false;

			panel(){
				pic = new PicPanel("/Peashooter/Frame0.png");
				setLayout(null);
				add(pic);
				
				pic_x = (int)((frame_width - pic.getWidth())/2);
				pic_y = (int)((frame_height - pic.getHeight())/2);
				
				pic.setLocation(pic_x, pic_y);  //定位
				//鼠标动作 监听器 注册
				addMouseListener(
					new MouseAdapter(){
						public void mousePressed(MouseEvent e){
							//检测 落点 是否在图片上,只有落点在图片上时 才起作用
							i = e.getX()/100;
							if(inPicBounds(e.getX(), e.getY()) && 0 <= i && i <= 3 && con.money >= card.car.get(i).getMoney()){
								begin_x = e.getX();
								begin_y = e.getY();
								inThePic = true;
							}
							//记录当前坐标
						}
						//释放
						public void mouseReleased(MouseEvent e){
							inThePic = false;	
							//repaint();
							con.money -=card.car.get(i).getMoney()/2;
							int a = (int)(Math.random()*5+1);

							if(card.getstyle(i) == 1)
								plant.addPeashooter(a);
							else if(card.getstyle(i) ==2)
								plant.addPea(a);
							else if(card.getstyle(i) == 3)
								plant.addThreepeator(a);
						}
					}
				);

				//鼠标移动 监听器 注册
				addMouseMotionListener(
					new MouseMotionAdapter(){
						public void mouseDragged(MouseEvent e){
							if(inThePic && checkPoint(e.getX(),e.getY())){
								//边界 检查
								pic_x =pic_x - (begin_x - e.getX());
								pic_y =pic_y - (begin_y - e.getY());
								//System.out.println("pic_x=" + pic_x);
								begin_x = e.getX();
								begin_y = e.getY();
								pic.setLocation(pic_x, pic_y);
							}
						}
					}
				);
			}
			//-------------帮助方法-----------------//
			//检测 点(px,py) 是否在图片上
			private boolean inPicBounds(int px,int py){
				if(px >= 0 && px <= 300 && py >= 0 && py <= 50)
					return true;
				else
					return false;
			}

			//越界 检查
			private boolean checkPoint(int px, int py){
				if(px <0 || py <0)
					return false;
				if(px > getWidth() || py > getHeight())
					return false;
				return true;
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
		//图片面板,只是用来放置图片
		class PicPanel extends JPanel{
			int p_width = 0;
			int p_height = 0;
			Image im = null;
		
			int i = 0; //temp var
			public PicPanel(String picName){
				ImageIcon imageIcon = new ImageIcon(getClass().getResource(picName));
				im = imageIcon.getImage();
			
				p_width = imageIcon.getIconWidth();
				p_height = imageIcon.getIconHeight();
				setBounds(0,0,p_width, p_height);
    		}
    
			public void paint(Graphics g){
				g.drawImage(im,0,0,p_width,p_height,null);
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

		BombMusic(){
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

	class settimes implements Runnable{
		Thread	xc	= null;
		long	jianGe;

		settimes(long jianGe){
			this.jianGe = jianGe;
			xc = new Thread(this);
			xc.start();
		}

		public void run(){
			while (true){
				try{
					xc.sleep(jianGe);

					if (this == ds1){
					
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
				catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args){
		new Start();
	}
}
