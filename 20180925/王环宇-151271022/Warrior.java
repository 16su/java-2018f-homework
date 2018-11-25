/*
 * ��Warriors�ӿڵ�Ҫ���£�����һ��Warrior�࣬����������д�����������
 * ����Warrior����ȴ���в�ͬ�Ĺ���
 * */
package javahw3;

public class Warrior implements WarriorInterface {
	/*
	 * Variables: name, function, position, team;
	 * Methods: getName(), getFunction(), getPosition(), changePosition(), toString(), showMe();
	 * */
	private String name;
	private String function;
	private int[] position;
	private String team;
	
	public Warrior(String name, String function, String team) {
		this.name = name;
		this.function = function;
		this.team = team;
		position = new int[2];
	}
	
	public String getName() {
		return name;
	}
	public String getFunction() {
		return function;
	}
	public void changeFunction(String function) {
		this.function = function;
	}
	public int[] getPosition() {
		return position;
	}
	public void changePosition(int m, int n) {
		position[0] = m;
		position[1] = n;
	}
	public String getTeam() {
		return team;
	}
	public void changeTeam(String team) {
		this.team = team;
	}

	//�����Դ���toString����������System.out�ĵ���
	public String toString() {
		return name;
	}
	//�Զ�����ʾ��������ӡ����������йؼ���Ϣ
	public void showMe() {
		System.out.println(name+" team:"+team+" "+function+" ["+position[0]+","+position[1]+"]");
	}
	
}
