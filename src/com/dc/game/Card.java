package com.dc.game;

import java.util.LinkedList;
import javax.swing.JButton;

public class Card {

	LinkedList<CardUtil>   car = new LinkedList<CardUtil>();
	Constant con = new Constant();
	public void setCardImages(){
		for (int i = 0; i < car.size(); i++) {
			car.get(i).setImage(car.get(i).money>Constant.money);
		}
	}
	public void addCard(){
		car.add(new CardUtil("/card/Peashooter",0,0,con.Peashooter_price,0,1));
		car.add(new CardUtil("/card/GatlingPea",100,0,con.GatLingPea_price,100,2));
		car.add(new CardUtil("/card/Threepeater",200,0,con.Threepeator_price,200,3));
	}
	public int getstyle(int index){
		return car.get(index).style;
	}
	public JButton[] getJButtom(){
		JButton [] a = new JButton[car.size()];
		for (int i = 0; i < car.size(); i++) {
			a[i] = car.get(i).getJButten();
		}
		return a;
	}
}
