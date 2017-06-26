public class BearingList extends List{
	private int lowestPriory;
	private int bigestPriory;
	
	public Bearing getBigestPrioryElement(){
		int maxPriory = lowestPriory;
		Node outNode = firstNode;
		Bearing tempB;
		
		for (Node tempNode = firstNode; tempNode != null; tempNode = tempNode.next){
			tempB = (Bearing) tempNode.obj;
			
			if (tempB == null)
				return null;
			
			if (tempB.getPriory() > maxPriory){
				maxPriory = tempB.getPriory();
				outNode = tempNode;
			}
			
			if (tempB.getPriory() == bigestPriory)
				break;
		}
		
		if (outNode.next != null && outNode.back != null){
			outNode.back.next = outNode.next;
			outNode.next.back = outNode.back;
			return (Bearing) outNode.obj;
		}
		
		if (outNode.next != null && outNode.back == null){
			outNode.next.back = null;
			firstNode = outNode.next;
			return (Bearing) outNode.obj;
		}
		
		if (outNode.next == null && outNode.back != null){
			outNode.back.next = null;
			lastNode = outNode.back;
			return (Bearing) outNode.obj;
		}
		
		if (outNode.next == null && outNode.back == null){
			Bearing temp = (Bearing) outNode.obj;
			outNode.obj = null;
			return temp;
		}
		
		return null;
	}
	
	//Override
	public String toString(){
		Bearing tempBearing;
		
		for (Node tempNode = firstNode; tempNode != null; tempNode = tempNode.next){
			tempBearing = (Bearing) tempNode.obj;
			System.out.print(tempBearing);
		}
		
		return "\n";
	}
	
	//Override
	public int len(int type){
		if (firstNode.obj == null)
			return 0;
		
		int i;
		Node tempNode = firstNode;
		Bearing bearing;
		
		for(i = 0; tempNode != null; tempNode = tempNode.next){
			bearing = (Bearing) tempNode.obj;
			if (bearing.getType() == type)
				i++;
		}
		
		return i;
	}
	
	public float getAverage(int type){
		if (firstNode.obj == null)
			return 0;
		
		int i = 0;
		float total = (float) 0.0;
		Node tempNode = firstNode;
		Bearing bearing;
		
		for(i = 0; tempNode != null; tempNode = tempNode.next){
			bearing = (Bearing) tempNode.obj;
			
			if (bearing.getType() == type){
				total += bearing.getProductionTime();
				i++;
			}
		}
		
		if (i == 0)
			return (float) 0.0;
		
		return total/i;
	}
	
	public BearingList(){
		lowestPriory = 1;
		bigestPriory = 3;	
	}
}