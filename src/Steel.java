public class Steel extends Bearing{
	private int id;
	
	//Override
	public String toString(){
		return "Steel - id: " + this.id + "\n";
	}
	
	public Steel(float startTime){
		priority = 3;
		type = 3;
		this.id = super.id;
		super.id++;
		MachineQueue = "310";
		this.startTime = startTime;
	}
}