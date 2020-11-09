package pouzivatelia;

import java.util.ArrayList;


public class Zamestnanec extends Pouzivatel {
	private ArrayList<Pacient> pacienti; //vsetci pacienti v ambulancii
	int pocetPo;
	public Zamestnanec(String meno,String priezvisko,String ulica,int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ) {
		super(meno, priezvisko,ulica,cislo_domu,obec, vek, telefon, email, id, typ);
		this.pacienti = new ArrayList<Pacient>();
	}
	public void setPacienti(ArrayList<Pacient> zoznam) {
		this.pacienti = zoznam;
	}
	
	public void vypisPacientov() {
		for(Pacient p: this.pacienti) {
			System.out.println(p.toString());
		}
	}
	public void zobrazPacienta(int id) {
		for(Pacient p: this.pacienti) {
			if(p.getId_typ().id==id) {
				System.out.println(p.toString());
				return;
			}
		}
		System.out.println("Pacient nenajdeny");
	}
	public void ZobrazPacienta(String priezvisko) {
		for(Pacient p: this.pacienti) {
			if(p.getPriezvisko().equals(priezvisko)) {
				System.out.println(p.toString());
				return;
			}
		}
		System.out.println("Pacient nenajdeny");
	}

}
