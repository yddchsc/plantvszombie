package com.dc.game;

import java.util.LinkedList;

public class Plant {

	
	private LinkedList<PlantUtil>		pea = new LinkedList<PlantUtil>();
	Constant con = new Constant();
	
	public void addwhich(int index,int hang){
		if(index==0){
			addPeashooter(hang);
		}else if(index==1){
			addPea(hang);
		}else if(index==2){
			addThreepeator(hang);
		}
	}
	public void addPeashooter(int hang){
		String path = "/Peashooter/Frame";
		pea.add(new PlantUtil(path,con.Peashooter_speed,1,1,hang));
	}
	public void addThreepeator(int hang){
		String path = "/Threepeater/Frame";
		pea.add(new PlantUtil(path,con.Threepeator_speed,1,3,hang));
	}
	
	public void addPea(int hang){
		String pa = "/GatlingPea/Frame" ;
			pea.add(new PlantUtil(pa,con.GatLingPea_speed,3,1,hang));
	}
	public LinkedList<PlantUtil> getpea(){
		return this.pea;
	}
	
}
