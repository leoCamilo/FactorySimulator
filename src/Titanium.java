public class Titanium extends Bearing{
	private int id;
	
	//Override
	public String toString(){
		return "Tinanium - id: " + this.id + "\n";
	}
	
	public Titanium(float startTime){
		priority = 3;
		type = 4;
		this.id = super.id;
		super.id++;
		MachineQueue = "31210";
		this.startTime = startTime;
	}
}