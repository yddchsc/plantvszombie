package com.dc.game;

public class Constant {
/*
 * 参数设置
 */
	 final int width = 1200;
	 final int length = 700;
	 static int money = 800;//初始金币
	 int spacing = 8;//子弹间距
	 //植物价格
	 int Peashooter_price = 200;
	 int GatLingPea_price = 400;
	 int Threepeator_price = 500;
	//植物子弹速度
	 int Peashooter_speed = 3;
	 int Threepeator_speed = 4;
	 int GatLingPea_speed = 6;
	//僵尸血量
	 int flag_blad = 7;//3 2 
	 int head_blad = 4;
	 //僵尸速度
	 int flag_speed = 4;//2  1
	 int head_speed = 4;
	 //难度设置：
	 static int difficulty = 2;//1-4
	 
	 
	 
	public static int getMoney() {
		return money;
	}
	public static void setMoney(int money) {
		Constant.money = money;
	}
	public int getSpacing() {
		return spacing;
	}
	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}
	public int getPeashooter_price() {
		return Peashooter_price;
	}
	public void setPeashooter_price(int peashooter_price) {
		Peashooter_price = peashooter_price;
	}
	public int getThreepeator_price() {
		return Threepeator_price;
	}
	public void setThreepeator_price(int threepeator_price) {
		Threepeator_price = threepeator_price;
	}
	public int getGatLingPea_price() {
		return GatLingPea_price;
	}
	public void setGatLingPea_price(int gatLingPea_price) {
		GatLingPea_price = gatLingPea_price;
	}
	public int getPeashooter_speed() {
		return Peashooter_speed;
	}
	public void setPeashooter_speed(int peashooter_speed) {
		Peashooter_speed = peashooter_speed;
	}
	public int getThreepeator_speed() {
		return Threepeator_speed;
	}
	public void setThreepeator_speed(int threepeator_speed) {
		Threepeator_speed = threepeator_speed;
	}
	public int getGatLingPea_speed() {
		return GatLingPea_speed;
	}
	public void setGatLingPea_speed(int gatLingPea_speed) {
		GatLingPea_speed = gatLingPea_speed;
	}
	public int getFlag_blad() {
		return flag_blad;
	}
	public void setFlag_blad(int flag_blad) {
		this.flag_blad = flag_blad;
	}
	public int getHead_blad() {
		return head_blad;
	}
	public void setHead_blad(int head_blad) {
		this.head_blad = head_blad;
	}
	public int getFlag_speed() {
		return flag_speed;
	}
	public void setFlag_speed(int flag_speed) {
		this.flag_speed = flag_speed;
	}
	public int getHead_speed() {
		return head_speed;
	}
	public void setHead_speed(int head_speed) {
		this.head_speed = head_speed;
	}
	public static int getDifficulty() {
		return difficulty;
	}
	public static void setDifficulty(int difficulty) {
		Constant.difficulty = difficulty;
	}

	
}
