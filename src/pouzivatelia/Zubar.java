package pouzivatelia;

import java.util.ArrayList;

import databaza.Reader;
import model.Termin;

public class Zubar extends Zamestnanec {
	public Zubar(String meno,String priezvisko,String ulica,int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ) {
		super(meno, priezvisko,ulica,cislo_domu,obec, vek, telefon, email, id, typ);
		this.terminy = Reader.nacitajTerminy(this.priezvisko,this.id_typ);
	}

	private ArrayList<Termin> terminy = new ArrayList<Termin>();
	
	public void prehladTerminov() {
		for(Termin t: this.terminy) {
			System.out.println(t.toString());
		}
	}

	public ArrayList<Termin> getTerminy() {
		return terminy;
	}
	public void vypisZubPacientov() {
		for(Pacient p: this.pacienti) {
			if(p.getMeno_zubara().equals(this.priezvisko)){
				System.out.println(p.toString());
			}
		}
	}
	public void setTerminy(ArrayList<Termin> terminy) {
		this.terminy = terminy;
	}
}
