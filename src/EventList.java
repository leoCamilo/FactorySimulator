public class EventList extends List{
	public void insertEvent(int type, float moment){		//insertion sort
		if (firstNode.obj == null){
			push(new Event(type,moment));
			return;
		}
		
		Event tempEvent = (Event) firstNode.obj;
		
		if (moment < tempEvent.getMoment()){
			push(new Event(type,moment));
			return;
		}
		
		Node newNode = new Node();
		newNode.obj = new Event(type,moment);
		
		for (Node tempNode = firstNode.next; tempNode != null; tempNode = tempNode.next){
			tempEvent = (Event) tempNode.obj;
			
			if (moment < tempEvent.getMoment()){
				tempNode.back.next = newNode;
				newNode.back = tempNode.back;
				newNode.next = tempNode;
				tempNode.back = newNode;
				return;
			}
			
			if (moment == tempEvent.getMoment()){				
				tempNode.back.next = newNode;
				newNode.back = tempNode.back;
				newNode.next = tempNode;
				tempNode.back = newNode;
				return;
			}
		}
		
		insert(newNode.obj);
	}
	
	//Override
	public void insertEvent(int type, float moment, Machine machine){
		if (firstNode.obj == null){
			push(new Event(type,moment,machine));
			return;
		}
		
		Event tempEvent = (Event) firstNode.obj;
		
		if (moment < tempEvent.getMoment()){
			push(new Event(type,moment,machine));
			return;
		}
		
		Node newNode = new Node();
		newNode.obj = new Event(type,moment,machine);
		
		for (Node tempNode = firstNode.next; tempNode != null; tempNode = tempNode.next){
			tempEvent = (Event) tempNode.obj;
			
			if (moment < tempEvent.getMoment()){
				tempNode.back.next = newNode;
				newNode.back = tempNode.back;
				newNode.next = tempNode;
				tempNode.back = newNode;
				return;
			}
			
			if (moment == tempEvent.getMoment()){
				tempNode.back.next = newNode;
				newNode.back = tempNode.back;
				newNode.next = tempNode;
				tempNode.back = newNode;
				return;
			}
		}
		
		insert(newNode.obj);
	}
}