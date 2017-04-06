package com.dc.game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class FrameUtil {

	private boolean live = true;
	public void setdeffit(long start,Zombie zombie){
		  long end = System.currentTimeMillis(); 
		  long a =(end-start)/1000;
		  if(a%(Constant.difficulty*5)==0){
			 if(live){
				 live = false;
				  if((end%10)<2){
					  zombie.addflag();
				  }else{
					  zombie.addhead();
				  }
			  }
		  }else{
			  live = true;
		  }
	}
	public int setjp(int mx,int my,Card card){
		LinkedList<CardUtil> c = card.car;
		for (int i = 0; i < c.size(); i++) {
			CardUtil temp = c.get(i);
			if(mx<(temp.x+100) && mx>temp.x && my>temp.y && my<(temp.y+60)){
				return i;	
			}
		}
		return c.size();
	}
	public void gensui(int mx,int my,Plant plant,Graphics g,Card card){
		int a = setjp(mx,my,card);
		String path = plant.getpea().get(a).pa;
		Image img =  (new ImageIcon(getClass().getResource(path+"0.jpg" ))).getImage();	//路径：图片-右键-属性-路径src/
		g.drawImage(img,mx,my,null);
	}
	public int getjp(int mx,int my){
		for (int i = 0; i < 5; i++) {
			if(mx>200 && mx<300 && my>(180+i*100) && my<(180+(i+1)*100)){
				return i+1;
			}
		}
		return 0;
	}
}
