package com.dc.game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Seed {
	int seedx=230;
	int seedy=290;
	int speed;
	Image path;
	int temp=0;
	public Seed(int seedx,int seedy,int speed){
		this.seedx = seedx;
		this.seedy = seedy;
		this.speed = speed;
		this.path=(new ImageIcon(getClass().getResource("/seed/Frame0.png") )).getImage();
	}
	public void draw(Graphics g){
		g.drawImage(path,seedx,seedy,null);
	}
	public void setImage(){
		if(temp<2)
			this.path=(new ImageIcon(getClass().getResource("/seed/Frame"+(temp++)+".png"))).getImage();
		else
			temp = 0;
	}
	public boolean walk(){
		if(seedx<1300){
			seedx += speed;
			return true;
		}
		else{
			return false;
		}
	}
}
