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
	JLabel jlpic = new JLabel();
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
		BackgroundImgPanel imgPanel = null;

		windows(){
			double panelWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();  
			double panelHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 25 - 25 - 20;//(两个25是内外两个窗口标题栏的高度,20是底部更新进度栏的高度)  
			imgPanel = new BackgroundImgPanel(panelWidth,panelHeight,"/bg/background1.jpg");  
			this.add(imgPanel,-1);//参数-1的作用是让这个背景图片面板保持在所有面板的最下面,相当于WEB中的z-index属性  
			Font font = new Font("宋体",Font.BOLD,80);
			jta.setBounds(800, 0, 400, 100);
			 
			jta.setEditable(false);
			jta.setFont(font);
			this.add(jta,0);
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			//this.setUndecorated(true);//设置隐藏标题栏

			mb = new panel();
			//mb.setOpaque(false); //把JPanel设置为透明 这样就不会遮住后面的背景 这样你就能在JPanel随意加组件了
			this.add(mb,1);

			exit = new windowslistener();
			this.addWindowListener(exit);

			this.repaint();
		}
		/** 
	     * 监听最外层窗口的resize事件,并根据新的窗口大小来调整背景图片的尺寸 
	     * @param evt 
	     */  
	    /*private void formComponentResized(java.awt.event.ComponentEvent evt) {                                        
	        // TODO add your handling code here:  
	        try{  
	            this.remove(imgPanel);  
	        }catch(Exception e){  
	        }  
	        imgPanel = null;  
	        Dimension newSize = evt.getComponent().getSize();  
	        imgPanel = new BackgroundImgPanel(newSize.getWidth(),newSize.getHeight()-70,"/bg/background1.jpg");  
	        this.add(imgPanel,-1);  
	    }  
	    */
		class windowslistener extends WindowAdapter{
			public void windowClosing(WindowEvent e){
				ds1.xc.stop();
				music.xc.stop();
				System.exit(0);
			}
		}
		class panel extends JPanel{
			Point point=new Point(0,0); //坐标点
			
			int i = -1;
			
			boolean inThePic = false;

			PlantUtil pl = null;

			panel(){
				
				//鼠标动作 监听器 注册
				addMouseListener(
					new MouseAdapter(){
						public void mousePressed(MouseEvent e){
							//检测 落点 是否在图片上,只有落点在图片上时 才起作用
							//i = (point.x-pic.getX())/100;
							i = e.getX()/100;
							if(inPicBounds(e.getX(), e.getY()) && 0 <= i && i < 3 && con.money >= card.car.get(i).getMoney()){
								inThePic = true;

								int a = (int)(e.getY());
								int b = (int)(e.getX());

								if(card.getstyle(i) == 1){
									String path = "/Peashooter/Frame";
									pl = new PlantUtil(path,con.Peashooter_speed,1,1,a,b,false);
								}
								else if(card.getstyle(i) ==2){
									String path = "/GatlingPea/Frame";
									pl = new PlantUtil(path,con.Threepeator_speed,1,3,a,b,false);
								}
								else if(card.getstyle(i) == 3){
									String pa = "/Threepeater/Frame";
									pl = new PlantUtil(pa,con.GatLingPea_speed,3,1,a,b,false);
								}
							}
							//记录当前坐标
						}
						//释放
						public void mouseReleased(MouseEvent e){	
							//repaint();
							if(inThePic){
								con.money -= card.car.get(i).getMoney()/2;
								int a = (int)(e.getY());
								int b = (int)(e.getX());

								if(card.getstyle(i) == 1)
									plant.addPeashooter(a,b);
								else if(card.getstyle(i) ==2)
									plant.addPea(a,b);
								else if(card.getstyle(i) == 3)
									plant.addThreepeator(a,b);
							}
							inThePic = false;
							pl = null;
						}
					}
				);

				//鼠标移动 监听器 注册
				addMouseMotionListener(
					new MouseMotionAdapter(){
						public void mouseDragged(MouseEvent e){
							if(inThePic && checkPoint(e.getX(),e.getY())){
								int a = (int)(e.getY());
								int b = (int)(e.getX());
								pl.move(a,b);
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
				
				//Image  tu=(new ImageIcon(getClass().getResource("/bg/background1.jpg"))).getImage();
				//g.drawImage(tu,0,100,null);		
				Image tu=(new ImageIcon(getClass().getResource("/SeedBank.png"))).getImage();
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
				if(pl != null){
					pl.setImage();
					pl.draw(g);	
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
