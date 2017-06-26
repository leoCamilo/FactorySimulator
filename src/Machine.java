public abstract class Machine{
	protected int type;							// 1-TORNO 2-FRESA 3-MANDRIL
	protected Bearing actualBearing = null;		// rolamento na maquina
	protected boolean broken = false;			// false- no isn't broken/ true - yes it's broken
	protected float breakFrequency;
	
	protected abstract float getTime(int bearingType);	// retorna o tempo necessario de cada rolamento
	
	public boolean empty(){
		if (actualBearing != null)
			return false;
		
		return true;
	}
	
	public abstract BearingList getBearingList();		// retorna a lista de rolamentos de cada tipo de maquina
	
	public float setBearing(Bearing bearing, float actualMoment){
		actualBearing = bearing;
		return actualMoment + getTime(bearing.getType());
	}
	
	public void sendBearingTo(BearingList bearingList){
		bearingList.push(this.actualBearing);
		actualBearing = null;
	}
	
	public Bearing getBearing(){
		return actualBearing;
	}
	
	public String toString(){
		if (actualBearing != null)
			return "type: " + type + " - Bearing: " + actualBearing.toString();
		return "type: " + type + " - Bearing: null\n";
	}
	
	public void setBrokenState(boolean state){
		broken = state;
	}
	
	public boolean itsBroken(){
		return this.broken;
	}
	
	public float getBreakFrequency(){
		return breakFrequency;
	}
}