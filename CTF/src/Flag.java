
public class Flag {
	private Player holder; 
	private  String status; //IS_IN_RED_BASE, IS_IN_BLUE_BASE, OWNED_BY_PLAYER;
	//private boolean isInBase;
	public Flag(Player holder, String status) throws Exception{
		if(status!= "IS_IN_RED_BASE"&& status !="IS_IN_BLUE_BASE"&& status != "OWNED_BY_PLAYER") {
			throw new Exception("INVALID_STATUS");
		}
		this.holder = holder;
		this.status = status;
		
	}
	
}
