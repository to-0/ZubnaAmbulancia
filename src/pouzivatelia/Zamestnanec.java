package pouzivatelia;

import java.util.ArrayList;

public class Zamestnanec extends Clovek {
	private ArrayList<Pacient> pacienti;
	int pocetPo;
	public Zamestnanec(String meno,String priezvisko,String ulica,int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ) {
		super(meno, priezvisko,ulica,cislo_domu,obec, vek, telefon, email, id, typ);
		this.pacienti = new ArrayList<Pacient>();
	}
	public void setPacienti(ArrayList<Pacient> zoznam) {
		this.pacienti = zoznam;
	}
	public Pacient vytvorPacienta(String krstne,String priezvisko, String ulica, int cislo, String obec, int vek,
			String tel,String email,String nick, String heslo) {
		int id = this.pocetPo+1;
		this.pocetPo++;
		Pacient p = new Pacient(krstne,priezvisko,ulica,cislo,obec,vek,tel,email,id,'P');
		p.pridajPrihl_udaje(nick, heslo);
		return p;
	}

}
