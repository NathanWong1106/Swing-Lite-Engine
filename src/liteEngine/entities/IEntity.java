package liteEngine.entities;

public interface IEntity {
	
	/**
	 * called by fixedUpdate in order to force a super() call
	 */
	public void update();
	
	/**
	 * called by update event
	 */
	public void fixedUpdate();
	
	/**
	 * called by onObjectDestroy in order to force a super() call
	 */
	public void onDestroy();
	
	/**
	 * called by a destruction call
	 */
	public void onObjectDestroy(); 
	
	/**
	 * called by constructors after instantiation or by receivers after dependencies are added
	 */
	public void awake();
}
