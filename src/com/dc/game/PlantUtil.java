package com.dc.game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class PlantUtil {
	
	int Plantx = 200;
	int Planty ;
	int speed;
	private Image path;
	private int temp=0;
	String pa;
	private int time1,time2;
	int xx,yy; //横着出几个，竖着出几个
	Constant con = new Constant();
	LinkedList<Seed> seed = new LinkedList<Seed>();
	
	//图片地址，速度，几行，几列
	public PlantUtil(String pa,int speed,int xx,int yy,int Planty,int Plantx){
		this.pa = pa;
		this.speed = speed;
		this.xx = xx;
		this.yy = yy;
		this.Planty = Planty*100+80;
		this.Plantx = Plantx;
		fashe();
	}
	public void setImage(){
		if(temp <10){
			this.path=(new ImageIcon(getClass().getResource(pa+(temp++)+".png" ))).getImage();
		}
		else{
			this.path=(new ImageIcon(getClass().getResource(pa+"0.png" ))).getImage();
			temp=1;
		}
	}
	
	public void draw(Graphics g){//画
		g.drawImage(path,Plantx,Planty,null);
		for (Seed s: seed) {
			g.drawImage(s.path,s.seedx,s.seedy,null);
		}
	}
	
	public void walk(){//移除子弹，添加子弹
		time2 = (int)(System.currentTimeMillis()/1000);
		if(seed.size()==0 && (time2-time1) > con.spacing){
			fashe();
		}else
		for (int i = 0; i < seed.size(); i++) {
			Seed s = seed.get(i);
			if(i==(seed.size()-1) && (time2-time1) > con.spacing){
				fashe();
			}
			s.setImage();
			if(!s.walk()){
				seed.remove(i);
			}
		}
	}
	
	public   void fashe(){//添加种子
		time1 = (int)(System.currentTimeMillis()/1000);
		
		for (int i = 0; i < xx; i++) {
			for (int j = 0; j < yy; j++) {
				Seed se=  new Seed(Plantx+30-i*15,Planty+10-j*15,speed);
				seed.add(se);
			}
		}
		
	}
	
	//判断是否与子弹碰
	public boolean pen(int jsx,int jsy){
		for (int i = 0; i < seed.size(); i++) {
				Seed s = seed.get(i);
			if(s.seedx>jsx && (s.seedy-50)>jsy && (s.seedy-120)<jsy){
				seed.remove(i);
				return false;
			}
		}
		return true;
	}
	
	//判断僵尸是否与植物碰
	 public boolean Peapeng(int jsx,int jsy){
		 if(this.Plantx>jsx && (jsy+80) == this.Planty){
			 return true;
		 }
		 return false;
	 }
}
