package pouzivatelia;

import java.util.ArrayList;

import model.Termin;

public class Zubar extends Zamestnanec {
	public Zubar(String meno,String priezvisko,String ulica,int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ) {
		super(meno, priezvisko,ulica,cislo_domu,obec, vek, telefon, email, id, typ);
		// TODO Auto-generated constructor stub
	}

	private ArrayList<Pacient> zoznamPacientov = new ArrayList<Pacient>();
	private ArrayList<Termin> terminy = new ArrayList<Termin>();
	
	public void pridajPacienta(Pacient p) {
		zoznamPacientov.add(p);
	}
	
	public void vypisPacientov() {
		for(Pacient pac: zoznamPacientov) {
			System.out.println(pac.getMeno() + pac.getPriezvisko() + "telefonne cislo"+pac.getTel_cislo());
		}
	}

	public ArrayList<Termin> getTerminy() {
		return terminy;
	}

	public void setTerminy(ArrayList<Termin> terminy) {
		this.terminy = terminy;
	}
}
