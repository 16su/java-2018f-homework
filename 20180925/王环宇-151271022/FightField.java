/*
 * ���������ĽǶȣ�����˫���ϳ���սʿ����������֯����ս���С�
 * @see class FightFileld, class Formation, Class Team.
 * @author why
 * @Time 2018-11-12
 * @version 3.0
 * 
 * version 4.0 �޸Ľ��飺
 * ����������������������ʹ�ó�����ӷ���Ҫ�� ������������������û������������
 * ���������һ�������⣬Bad��Ӫ�У�һ��һС�����棻Good��Ӫ�У������Enum֮��Ƚϻ��Щ
 * ��Java�У����������������Ƿ�Ҳ����ô�򵥣�
 * 
 * ʹ�÷�����ɴ��룬���Է����е���Щ������ͬʱҲ�������ʱ�����ع�һ�·��͵�һЩ����
 * 
 * */

package javahw3;
import java.util.Random;
//import java.util.ArrayList;
import java.util.Scanner;



public class FightField {
	/*
	 * ������Ҫ��ִ����ڣ��ڴ����е���ս��������սʿ��ѡ�����ͣ���ʾս����Ϣ.
	 * <p>
	 *     �ڴ���������Warrior�ĳ�Ա����Ϊ����ս���������Ա<br>
	 *     �����л��õ���Team��ĳ�Ա�Լ�Formation��ĳ�Ա<br>
	 *     ���е�ִ���Զ����ڱ�������ɡ�<br>
	 * @param true: ��ȷ, false: ����
	 * @return�� boolean 
	 * */
	static int SHUFFLE = 50;
	
	private static final int N = 15;
	public Warrior[][] fields;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		FightField ff = new FightField();
		//��¼ÿ�ӵ�սʿ������ʾ�������Ϣ
		Team teamGood = new Team("GoodMan");
		Team teamBad = new Team("BadMan");
		loading(teamGood, teamBad);
		try {
			teamGood.checkMember();
			teamBad.checkMember();
		}catch(MyException e) {
		}
		teamGood.showTeam();
		teamBad.showTeam();
		//��ʼ������
		Formation.initialization();
		/*-----------------------ROUND 1------------------------*/
		System.out.println("ROUND1");
		ff.round(teamGood, teamBad, in);
		ff.showFields();
		
		System.out.println();
		/*-----------------------ROUND 2------------------------*/
		ff.initialization();
		System.out.println("ROUND2");
		ff.round(teamGood, teamBad, in);
		ff.showFields();
		
		in.close();
		
		teamGood.showTeam();
		teamBad.showTeam();
	}
	//Constructor
	private FightField() {
		initialization();
	}

	private void initialization() {
		fields = new Warrior[N][N];
	}
	
	private static void loading(Team teamGood, Team teamBad) {
		System.out.println("���������ϳ����жԾ��Ķ���......");
		String name = "�������������";
		for (int i=0; i<7; i++) {
			teamGood.add(new CalabashBrothers(name.charAt(i) + "��", ""));
		}
		teamGood.add(new Warrior("��үү", "��Ϸ", teamBad.getSide()));
		teamBad.add(new Warrior("Ы�Ӿ�", "���", teamBad.getSide()));
		for (int i=0; i<5; i++) {
			teamBad.add(new Warrior("С��"+i, "���", teamBad.getSide()));
		}
		teamBad.add(new Warrior("С��"+6, "���", teamGood.getSide()));
		teamBad.add(new Warrior("�߾�", "��Ϸ", teamBad.getSide()));
		// shuffle
		shuffle(teamGood);
		shuffle(teamBad);
	}
	
	public static void shuffle(Team team) {
		Random rdm = new Random();
		for(int i=0; i<SHUFFLE; i++) {
			int t1 = Math.abs(rdm.nextInt()) % team.team.size();
			int t2 = Math.abs(rdm.nextInt()) % team.team.size();
			if(t1 == t2)
				continue;
			else {
				Warrior w;
				w = team.team.get(t1);
				team.team.set(t1, team.team.get(t2));
				team.team.set(t2, w);
			}
		}
	}
	
	// show the fields
	private void showFields() {
		System.out.println("***************************************************ս���������****************************************************");
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(fields[i][j]==null)
					System.out.print("---");
				else
					System.out.print(fields[i][j]);
				System.out.print("\t");
			}
			System.out.println();
		}
		System.out.println("***************************************************ս���������****************************************************");
	}
	
	//��˫�����佫����ս��
	private void goBattle(Team team, int[][] place) {
		team.goBattle(fields, place);
	}
	
	//�Զ���ѡ��˫��������
	private void round(Team teamGood, Team teamBad, Scanner in) {
		System.out.println("************˫��սʿ�ϳ�*************");
		System.out.println("********��ѡ�������ϳ�������*********");
		System.out.println("**********0----------������**********");
		System.out.println("**********1----------������**********");
		System.out.println("**********2----------������**********");
		System.out.println("**********3----------������**********");
		int f1 = -1;
		// deal with the error: index out of bounds
		this.goBattle(teamGood, Formation.getForm(0, 1));
		try {
			f1 = in.nextInt();
			this.goBattle(teamBad, Formation.getForm(f1, 2));
		}catch( IndexOutOfBoundsException e){
			System.out.println("your input is out of bound! please input again!");
			f1 = in.nextInt();
			this.goBattle(teamBad, Formation.getForm(f1, 2));
		}
	}
}


