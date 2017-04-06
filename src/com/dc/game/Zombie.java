package com.dc.game;

import java.util.LinkedList;

public class Zombie {
	private LinkedList<ZombieUtil> zombie = new LinkedList<ZombieUtil>();	
	Constant con = new Constant();
	public LinkedList<ZombieUtil> gethead(){
		return this.zombie;
	}
	public void addflag(){
		//(String path,int blad,int speed)
		String path = "/FlagZombie/Frame";
		zombie.add(new ZombieUtil(path,con.flag_blad,con.flag_speed));
	}
	public void addhead(){
		//(String path,int blad,int speed)
		String path = "/HeadWalk1/Frame";
		zombie.add(new ZombieUtil(path,con.head_blad,con.head_speed));
	}
	
	public void  ispeng(LinkedList<PlantUtil> pea){
		if(pea.size() != 0){
			for (int k = 0; k < pea.size(); k++) {
				PlantUtil pe = pea.get(k);
				if(!zombie.isEmpty()){
					for (int i = 0; i < zombie.size(); i++) {
						ZombieUtil	js = zombie.get(i);
						if(!pe.pen(js.jsx, js.jsy))		{	js.blad--;Constant.money+=50;}
						if(js.blad==0)					{	zombie.remove(i);Constant.money+=100;}
						if(pe.Peapeng(js.jsx, js.jsy))    {  pea.remove(k);}
					}		
				}
			}
		}
	}

}
