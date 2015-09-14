package cl.doman.db.model.field;

public enum BooleanType {
	TRUE(true),FALSE(false);
	
	private boolean bool;

	private BooleanType(boolean bool){
		this.bool = bool;
	}
	
	public boolean getValue(){
		return bool;
	}
}
