public abstract class Bearing{
	protected int priority;
	protected int type;
	protected static int id = 1;
	protected String MachineQueue;		// ordem de execucao no maquinario, por tipo
	protected float startTime;
	protected float endTime;
	
	public int nextMachine(){			//retorna o tipo da proxima maquina, e 0 se acabou
		String tempStr;
		
		try{
		
			tempStr = MachineQueue.substring(0,1);
			MachineQueue = MachineQueue.substring(1);
			
		}catch(StringIndexOutOfBoundsException e){
			return 0;
		}
		
		return Integer.parseInt(tempStr);
	}
	
	public int getType(){
		return this.type;
	}
	
	public int getPriory(){
		return this.priority;
	}
	
	public void setEndTime(float time){
		this.endTime = time;
	}
	
	public float getProductionTime(){
		return  endTime - startTime;
	}
}