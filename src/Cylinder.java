public class Cylinder extends Bearing{
	private int id;
	
	//Override
	public String toString(){
		return "Cylinder - id: " + this.id + "\n";
	}
	
	public Cylinder(float startTime){
		priority = 1;
		type = 1;
		this.id = super.id;
		super.id++;
		MachineQueue = "2130";
		this.startTime = startTime;
	}
}