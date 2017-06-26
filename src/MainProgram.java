public class MainProgram{
	public static void main(String args[]){
		try{
			
			// modo de chamada: java MainProgram ((float) tempoDeSimulação) ((int) numTorno) ((int) numFresa) ((int) numMandril)
			Simulator simulation = new Simulator(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
			simulation.runSimulation(Float.parseFloat(args[0]));
			simulation.printResults();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}