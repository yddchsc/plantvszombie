package com.dc.game;

import java.util.LinkedList;

public class Plant {

	
	private LinkedList<PlantUtil> pea = new LinkedList<PlantUtil>();
	Constant con = new Constant();
	
	public void addwhich(int index,int hang,int lie){
		if(index==0){
			addPeashooter(hang,lie);
		}else if(index==1){
			addPea(hang,lie);
		}else if(index==2){
			addThreepeator(hang,lie);
		}
	}
	public void addPeashooter(int hang,int lie){
		String path = "/Peashooter/Frame";
		pea.add(new PlantUtil(path,con.Peashooter_speed,1,1,hang,lie,true));
	}
	public void addThreepeator(int hang,int lie){
		String path = "/Threepeater/Frame";
		pea.add(new PlantUtil(path,con.Threepeator_speed,1,3,hang,lie,true));
	}
	
	public void addPea(int hang,int lie){
		String pa = "/GatlingPea/Frame" ;
		pea.add(new PlantUtil(pa,con.GatLingPea_speed,3,1,hang,lie,true));
	}
	public LinkedList<PlantUtil> getpea(){
		return this.pea;
	}
	
}
