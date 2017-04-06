package com.dc.game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CardUtil {

	int x;
	int y;
	int money;
	private Image img;
	String path;
	int temp;
	int style;
	Font font = new Font("宋体",Font.BOLD,80);
	public CardUtil(String path,int x,int y,int money,int temp,int style){
		this.path = path;
		this.x = x;
		this.y = y;
		this.money = money;
		this.temp = temp;
		this.style = style;
		this.img=(new ImageIcon(getClass().getResource(path+".png"))).getImage();
	}
	public JButton getJButten(){
		JButton jb = new JButton();
		jb.setBounds(temp, 60, 100, 40);
		jb.setText(money+"");
		jb.setActionCommand("press");//设置按钮响应
		return jb;
	}
	public int getMoney(){
		return money;
	}
	public void setImage(boolean q){
		if(q){
			this.img=(new ImageIcon(getClass().getResource(path+"G.png"))).getImage();
		}
		else{
			this.img=(new ImageIcon(getClass().getResource(path+".png"))).getImage();
		}
	}
	public void draw(Graphics g){
		g.drawImage(img,x,y,null);
	}
}
