package Character;

public abstract class Character {
	private String Name;
	private int level;
	private long HP;
	private String category;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public long getHP() {
		return HP;
	}
	public void setHP(long hP) {
		HP = hP;
	}
}
