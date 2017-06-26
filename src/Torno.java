import java.util.Random;

public class Torno extends Machine{
	protected static BearingList tornoList= new BearingList();	//não foi declarado na classe abstrata pois cada tipo tem a sua propria lista
	
	//Override
	protected float getTime(int bearingType){
		Random randNum = new Random();
		float num = randNum.nextFloat();
		
		switch (bearingType){
			case 1: return (float) (2.0 * 0.8 * num);	// 1 - Cylinder
			case 2: return (float) (2.0 * 1.8 * num);	// 2 - Conical
			case 3: return (float) (2.0 * 1.0 * num);	// 3 - Steel spherical
			case 4: return (float) (2.0 * 1.6 * num);	// 4 - Titanium spherical
		}
		
		return (float) 0.0;
	}
	
	//Override
	public BearingList getBearingList(){
		return tornoList;
	}
	
	public Torno(){
		type = 1;
		breakFrequency = (float) 3.0;
	}
}