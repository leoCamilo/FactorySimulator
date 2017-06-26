public class Conical extends Bearing{
	private int id;
	
	//Override
	public String toString(){
		return "Conical - id: " + this.id + "\n";
	}
	
	public Conical(float startTime){
		priority = 2;
		type = 2;
		this.id = super.id;
		super.id++;
		MachineQueue = "310";
		this.startTime = startTime;
	}
}