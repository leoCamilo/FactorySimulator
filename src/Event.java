public class Event{
	private int type;	
	// 1 - chegada de rolamento - Cylinder
	// 2 - chegada de rolamento - Conical
	// 3 - chegada de rolamento - Steel
	// 4 - chegada de rolamento - Titanium
	// 5 - rolamento pronto em uma ou mais maquinas
	// 6 - quebra de maquina
	// 7 - maquina concertada
	// 0 - fim de execucao
	private float moment;
	private Machine machine;
	
	public int getType(){
		return this.type;
	}
	
	public float getMoment(){
		return this.moment;
	}
	
	public String toString(){
		return "moment: " + moment;
	}
	
	public void setMachine(Machine machine){
		this.machine = machine;
	}
	
	public Machine getMachine(){
		return this.machine;
	}
	
	public Event(int type, float moment){
		this.type = type;
		this.moment = moment;
	}
	
	public Event(int type, float moment, Machine machine){
		this.type = type;
		this.moment = moment;
		this.machine = machine;
	}
}