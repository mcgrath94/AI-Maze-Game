package ie.gmit.sw.ai;


public class Player {

	private Node playerNode;
	private int weaponCount;
	private int life = 20;	

	public Node getPlayerNode()
	{
		return playerNode;
	}
	public void setPlayerNode(Node playerNode) 
	{
		this.playerNode = playerNode;
	}
	
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	
	public int getWeaponCount() {
		return weaponCount;
	}
	public void getWeaponCount(int weaponCount) {
		this.weaponCount = weaponCount;
	}
	

}

