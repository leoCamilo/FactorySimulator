import java.util.Random;

public class Mandril extends Machine{
	protected static BearingList mandrilList = new BearingList();	//não foi declarado na classe abstrata pois cada tipo tem a sua propria lista
	
	//Override
	protected float getTime(int bearingType){
		Random randNum = new Random();
		float num = randNum.nextFloat();
		
		switch (bearingType){
			case 1: return (float) (2.0 * 1.2 * num);		// 1 - Cylinder
			case 2: return (float) (2.0 * 2.1 * num);		// 2 - Conical
			case 3: return (float) (2.0 * 1.4 * num); 		// 3 - Steel spherical
			case 4: return (float) (2.0 * 1.5 * num); 		// 4 - Titanium spherical
		}
		
		return (float) 0.0;
	}
	
	//Override
	public BearingList getBearingList(){
		return mandrilList;
	}
	
	public Mandril(){
		type = 3;
		breakFrequency = (float) 1.0;
	}
}