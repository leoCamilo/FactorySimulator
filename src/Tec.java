public class Tec{
	private boolean busy = false;
	private float timeToFix;
	
	public boolean itsBusy(){
		return busy;
	}
	
	public void setBusyState(boolean state){
		this.busy = state;
	}
	
	public float getReadyTime(float instant){
		return instant + timeToFix;
	}
	
	public Tec(float time){
		this.timeToFix = time;
	}
}