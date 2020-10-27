package pouzivatelia;

import java.util.ArrayList;

import model.Clovek;

public class Zubar extends Zamestnanci {
	public Zubar(String firstn, String secondN, int age, String phone_num,String email) {
		super(firstn, secondN, age, phone_num,email);
		// TODO Auto-generated constructor stub
	}

	ArrayList<Pacient> zoznamPacientov = new ArrayList<Pacient>();
	
	public void vypisPacientov() {
		for(Pacient pac: zoznamPacientov) {
			System.out.println(pac.meno + pac.priezvisko + "telefonne cislo"+pac.tel_cislo);
		}
	}
}
