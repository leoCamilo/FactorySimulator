import java.util.Random;

public class Fresa extends Machine{
	protected static BearingList fresaList = new BearingList();	//não foi declarado na classe abstrata pois cada tipo tem a sua propria lista
	
	//Override
	protected float getTime(int bearingType){
		Random randNum = new Random();
		float num = randNum.nextFloat();
		
		switch (bearingType){
			case 1: return (float) (2.0 * 0.5 * num);		// 1 - Cylinder
			case 3: return (float) (2.0 * 0.5 * num); 		// 3 - Steel spherical
			case 4: return (float) (2.0 * 0.6 * num); 		// 4 - Titanium spherical
		}
		
		return (float) 0.0;
	}
	
	//Override
	public BearingList getBearingList(){
		return fresaList;
	}
	
	public Fresa(){
		type = 2;
		breakFrequency = (float) 0.5;
	}
}