package pouzivatelia;

import java.util.ArrayList;


public class Zamestnanec extends Pouzivatel {
	//nemam dovod to davat private neviem....
	ArrayList<Pacient> pacienti; //vsetci pacienti v ambulancii
	int pocetPo; //pocet pouzivatelov
	public Zamestnanec(String meno,String priezvisko,String ulica,int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ) {
		super(meno, priezvisko,ulica,cislo_domu,obec, vek, telefon, email, id, typ);
		this.pacienti = new ArrayList<Pacient>();
	}
	public void setPacienti(ArrayList<Pacient> zoznam) {
		this.pacienti = zoznam;
	}
	public ArrayList<Pacient> getPacienti() {
		return this.pacienti;
	}
	
	public void vypisPacientov() {
		System.out.println("Idem vypisat pacientov");
		for(Pacient p: this.pacienti) {
			System.out.println(p.toString());
		}
	}
	public void zobrazPacienta(int id) {
		for(Pacient p: this.pacienti) {
			if(p.getId_typ().getId()==id) {
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
	public int getPocetPo() {
		return pocetPo;
	}
	public void setPocetPo(int pocetPo) {
		this.pocetPo = pocetPo;
	}
}
