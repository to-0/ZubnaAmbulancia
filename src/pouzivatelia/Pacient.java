package pouzivatelia;
import java.util.ArrayList;

import model.Clovek;
import model.Termin;

public class Pacient extends Clovek {
	public Pacient(String firstn, String secondN, int age, String phone_num,String email) {
		super(firstn, secondN, age, phone_num,email);
		// TODO Auto-generated constructor stub
	}

	ArrayList<Termin> terminy = new ArrayList<Termin>();
}
