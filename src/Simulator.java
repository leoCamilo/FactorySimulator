import java.util.Random;

public class Simulator{
	private MachineList tornoList;
	private MachineList fresaList;
	private MachineList mandrilList;
	
	BearingList finishBearing;
	
	private float Gera_Exponencial(float avg)	// função copiada do trabalho
	{
		float u = 0; /* Gera randomicamente um numero entre 0 e 1 */
		do u = (new Random().nextFloat());
		while ((u==0) || (u==1));
		return (-avg * (float) Math.log(u));
	}
	
	private int prob(){
		if (new Random().nextInt(100) < 90)
			return 3;
		return 4;
	}
	
	private EventList getEventList(float end){		// retorna a lista inicial de eventos
		EventList list = new EventList();
		list.insertEvent(0, end);
		
		//Insere os primeiros tempos de chegada de cada tipo de rolamento
		list.insertEvent(1, Gera_Exponencial((float) 21.5));
		list.insertEvent(2, Gera_Exponencial((float) 19.1));
		list.insertEvent(prob(), Gera_Exponencial((float) 8.0));
		
		//Insere os primeiros tempos de quebra de cada maquina
		float time;
		Machine tempMachine;
		MachineList tempTornoList = new MachineList();
		MachineList tempFresaList = new MachineList();
		MachineList tempMandrilList = new MachineList();
		
		tempMachine = (Machine) tornoList.pop();
		
		while (tempMachine != null){
			tempTornoList.push(tempMachine);
			time = (float) (2.0 * 10080.0 / tempMachine.getBreakFrequency()) * new Random().nextFloat();	// calcular tempo
			list.insertEvent(6,time,tempMachine);
			tempMachine = (Machine) tornoList.pop();
		}
		
		tempMachine = (Machine) fresaList.pop();
		
		while (tempMachine != null){
			tempFresaList.push(tempMachine);
			time = (float) (2.0 * 10080.0 / tempMachine.getBreakFrequency()) * new Random().nextFloat();	// calcular tempo
			list.insertEvent(6,time,tempMachine);
			tempMachine = (Machine) fresaList.pop();
		}
		
		tempMachine = (Machine) mandrilList.pop();
		
		while (tempMachine != null){
			tempMandrilList.push(tempMachine);
			time = (float) (2.0 * 10080.0 / tempMachine.getBreakFrequency()) * new Random().nextFloat();	// calcular tempo
			list.insertEvent(6,time,tempMachine);
			tempMachine = (Machine) mandrilList.pop();
		}
		
		tornoList = tempTornoList;
		fresaList = tempFresaList;
		mandrilList = tempMandrilList;
		
		return list;
	}
	/*
	private void printSimulation(String time, int type){
		System.out.print("----------------------------------------------------------- " + "type: " + type + " " + time);
		System.out.print("\ntornos: \n");
		System.out.print(tornoList);
		System.out.print("\nfresas: \n");
		System.out.print(fresaList);
		System.out.print("\nmandris: \n");
		System.out.print(mandrilList);
		System.out.print("\nprontos: \n");
		System.out.print(finishBearing);
	}
	*/
	public void printResults(){
		System.out.println("");
		System.out.println(" " + finishBearing.len(1) + " cilindros produzidos -------------- tempo medio: " + finishBearing.getAverage(1));
		System.out.println(" " + finishBearing.len(2) + " conicos produzidos ---------------- tempo medio: " + finishBearing.getAverage(2));
		System.out.println(" " + finishBearing.len(3) + " esfericos de aco produzidos ------- tempo medio: " + finishBearing.getAverage(3));
		System.out.println(" " + finishBearing.len(4) + " esfericos de titanium produzidos -- tempo medio: " + finishBearing.getAverage(4));
	}
	
	public void runSimulation(float endTime){
		EventList eventList = getEventList(endTime);
		Event actualMoment = (Event) eventList.pop();
		
		BearingList tempBearingList;
		Machine tempMachine = null;
		Bearing tempBearing = null;
		
		float instant;
		Tec technician = new Tec((float) 20.0);
		
		end: while (actualMoment != null){
			instant = actualMoment.getMoment();
			
			switch (actualMoment.getType()){
				case 0:
					break end;
					
				case 1:
					Cylinder newCylinder = new Cylinder(instant);
					tempBearingList = (BearingList) tornoList.getBearingList();
					tempBearingList.push(newCylinder);
					eventList.insertEvent(1, instant + Gera_Exponencial((float) 21.5)); // calcula a chegada do prox
					break;
				
				case 2:
					Conical newConical = new Conical(instant);
					tempBearingList = (BearingList) tornoList.getBearingList();
					tempBearingList.push(newConical);
					eventList.insertEvent(2, instant + Gera_Exponencial((float) 19.1)); // calcula a chegada do prox
					break;
					
				case 3:
					Steel newSteel = new Steel(instant);
					tempBearingList = (BearingList) fresaList.getBearingList();
					tempBearingList.push(newSteel);
					eventList.insertEvent(prob(), instant + Gera_Exponencial((float) 8.0)); // calcula a chegada do prox
					break;
					
				case 4:
					Titanium newTitanium = new Titanium(instant);
					tempBearingList = (BearingList) fresaList.getBearingList();
					tempBearingList.push(newTitanium);
					eventList.insertEvent(prob(), instant + Gera_Exponencial((float) 8.0)); // calcula a chegada do prox
					break;
				
				// ... para novos rolamentos
				
				case 5:	// algum rolamento pronto
					tempMachine = actualMoment.getMachine();
					tempBearing = tempMachine.getBearing();
					
					if (tempMachine.itsBroken())
						break;
					
					switch (tempBearing.nextMachine()){
						case 1:
							tempMachine.sendBearingTo(tornoList.getBearingList());
							break;
							
						case 2:
							tempMachine.sendBearingTo(fresaList.getBearingList());
							break;
							
						case 3:
							tempMachine.sendBearingTo(mandrilList.getBearingList());
							break;
							
						default:
							tempBearing.setEndTime(instant);
							tempMachine.sendBearingTo(finishBearing);
					}
					
					break;
					
				case 6:	// alguma maquina quebrou
					tempMachine = actualMoment.getMachine();
					tempMachine.setBrokenState(true);
					break;
					
				case 7:	// alguma maquina comcertada - ao ser concertada o rolamento volta a ser tradado do 0
					tempMachine = actualMoment.getMachine();
					tempMachine.setBrokenState(false);
					technician.setBusyState(false);
					
					float time = (float) (2.0 * 10080.0 / tempMachine.getBreakFrequency()) * new Random().nextFloat();	// calcular tempo
					eventList.insertEvent(6,(instant + time),tempMachine);
					
					if (tempMachine.getBearing() != null)
						eventList.insertEvent(5, tempMachine.setBearing(tempMachine.getBearing(), instant), tempMachine);
					
					break;
			}
			
			// maquinas olham suas filas de rolamentos e tratam caso haja algum para tratar
			// tornos ---------------------------------------------------------
			tempBearingList = (BearingList) tornoList.getBearingList();
			tempMachine = tornoList.getFreeMachine();
			
			if (tempMachine != null)
				tempBearing = tempBearingList.getBigestPrioryElement();
			
			while (tempBearing != null && tempMachine != null){
				eventList.insertEvent(5, tempMachine.setBearing(tempBearing, instant), tempMachine);
				tempMachine = tornoList.getFreeMachine();
				
				if (tempMachine != null)
					tempBearing = tempBearingList.getBigestPrioryElement();
			}
			
			// fresas ---------------------------------------------------------
			tempBearingList = (BearingList) fresaList.getBearingList();
			tempMachine = fresaList.getFreeMachine();
			
			if (tempMachine != null)
				tempBearing = tempBearingList.getBigestPrioryElement();
			
			while (tempBearing != null && tempMachine != null){
				eventList.insertEvent(5, tempMachine.setBearing(tempBearing, instant), tempMachine);
				tempMachine = fresaList.getFreeMachine();
			
				if (tempMachine != null)
					tempBearing = tempBearingList.getBigestPrioryElement();
			}
			
			// mandris ---------------------------------------------------------
			tempBearingList = (BearingList) mandrilList.getBearingList();
			tempMachine = mandrilList.getFreeMachine();
			
			if (tempMachine != null)
				tempBearing = tempBearingList.getBigestPrioryElement();
			
			while (tempBearing != null && tempMachine != null){
				eventList.insertEvent(5, tempMachine.setBearing(tempBearing, instant), tempMachine);
				tempMachine = mandrilList.getFreeMachine();
			
				if (tempMachine != null)
					tempBearing = tempBearingList.getBigestPrioryElement();
			}
			
			//printSimulation(actualMoment.toString(), actualMoment.getType());
			actualMoment = (Event) eventList.pop();
			
			// maquinas quebradas ----------------------------------------------
			if (!technician.itsBusy()){
				tempMachine = tornoList.getBrokenMachine();
				
				if (tempMachine != null && !technician.itsBusy()){	// para mais de um tec. basta ser um while e criar uma lista de tecnicos
					eventList.insertEvent(7, technician.getReadyTime(instant), tempMachine);
					technician.setBusyState(true);
					continue;
				}
				
				tempMachine = fresaList.getBrokenMachine();
				
				if (tempMachine != null && !technician.itsBusy()){	// para mais de um tec. basta ser um while e criar uma lista de tecnicos
					eventList.insertEvent(7, technician.getReadyTime(instant), tempMachine);
					technician.setBusyState(true);
					continue;
				}
				
				tempMachine = mandrilList.getBrokenMachine();
				
				if (tempMachine != null && !technician.itsBusy()){	// para mais de um tec. basta ser um while e criar uma lista de tecnicos
					eventList.insertEvent(7, technician.getReadyTime(instant), tempMachine);
					technician.setBusyState(true);
					continue;
				}
			}
		}
		
		return;
	}
	
	public Simulator(int torno, int fresa, int mandril){
		tornoList = new MachineList();
		fresaList = new MachineList();
		mandrilList = new MachineList();
		
		finishBearing = new BearingList();
		
		for (int i = 0; i < torno; i++)
			tornoList.push(new Torno());
		
		for (int i = 0; i < fresa; i++)
			fresaList.push(new Fresa());
		
		for (int i = 0; i < mandril; i++)
			mandrilList.push(new Mandril());
	}
}