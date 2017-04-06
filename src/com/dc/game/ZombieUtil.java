package com.dc.game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ZombieUtil {

	int jsx = 1100;
	int jsy ;
	int speed;
	int blad;//
	Image wa;
	String path;
	int temp =0;
	
	public   void setjsy(){
		int a = (int)(Math.random()*4)+1;
		jsy = a*100;
	}
	
	public  ZombieUtil(String path,int blad,int speed){
		this.path  = path;
		this.blad = blad;
		this.speed = speed;
		this.wa=(new ImageIcon(getClass().getResource(path+"0.png"))).getImage();
		setjsy();
	}
	public void walk(){
		if(jsx>100){
			jsx -= speed;
		}
		else{
			Constant.money-=200;
			jsx = 1100;
		}
	}
	public void draw(Graphics g){
		g.drawImage(wa,jsx,jsy,null);
	}
	public void setImage(){
		if(temp <10)
			this.wa=(new ImageIcon(getClass().getResource(path+(temp++)+".png") )).getImage();
		else
			temp = 0;
	}
}
