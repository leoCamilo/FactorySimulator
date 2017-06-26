public class MachineList extends List{
	public Machine getFreeMachine(){
		Machine tempMachine;
		
		for (Node tempNode = firstNode; tempNode != null; tempNode = tempNode.next){
			tempMachine = (Machine) tempNode.obj;
			
			if (tempMachine.getBearing() == null && !tempMachine.itsBroken())
				return tempMachine;
		}
		
		return null;
	}
	
	public Machine getBrokenMachine(){
		Machine tempMachine;
		
		for (Node tempNode = firstNode; tempNode != null; tempNode = tempNode.next){
			tempMachine = (Machine) tempNode.obj;
			
			if (tempMachine.itsBroken())
				return tempMachine;
		}
		
		return null;
	}
	
	public BearingList getBearingList(){		// retorna a lista de rolamentos de uma lista de maquinas
		Machine temp = (Machine) firstNode.obj;
		
		if (temp != null)
			return temp.getBearingList();
		
		return null;
	}
	
	public String toString(){
		Machine tempMachine;
		
		for (Node tempNode = firstNode; tempNode != null; tempNode = tempNode.next){
			tempMachine = (Machine) tempNode.obj;
			System.out.print(tempMachine);
		}
		
		return "";
	}
}