package pouzivatelia;
import java.util.ArrayList;
import java.util.Scanner;

import databaza.Reader;
import model.Termin;

public class Pacient extends Pouzivatel {
	private String meno_zubara;
	private String rodne_cislo;
	private String poistovna;
	ArrayList<Termin> terminy = new ArrayList<Termin>();
	public Pacient(String meno,String priezvisko,String ulica,int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ) {
		super(meno, priezvisko,ulica,cislo_domu,obec, vek, telefon, email, id, typ);
		this.terminy = Reader.nacitajTerminy(this.getId_typ().getId());
		this.poistovna = "Nezadane";
		this.rodne_cislo = "Nezadane";
		this.meno_zubara= "Nezadane";
	}
	public Pacient(String meno,String priezvisko,String ulica,int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ,
			String rodne_cislo,String poistovna, String meno_z){
		super(meno, priezvisko,ulica,cislo_domu,obec, vek, telefon, email, id, typ);
		this.terminy = Reader.nacitajTerminy(this.getId_typ().getId());
		this.poistovna = poistovna;
		this.rodne_cislo = rodne_cislo;
		this.meno_zubara= meno_z;
	}
	public String toString() {
		String s= "Meno pacienta: "+this.getMeno()+" "+this.getPriezvisko()+"\nVek: " + this.getVek()+"\nTelefonne cislo:"
				+this.getTel_cislo() + "\n Email: "+this.getEmail() +"\nRodne cislo"+this.rodne_cislo+"\nPoistovna:"+this.poistovna
				+"\nMeno osetrujuceho zubara: "+this.meno_zubara;
		return s;
	}
	public String toWriter() {
		System.out.println("pacient to writer");
		String s = this.getId_typ().getId()+":"+this.getId_typ().getTyp()+":"+this.meno+":"+this.priezvisko+":"+this.adresa.getUlica()+":"+
	this.adresa.getCislo_domu()+":"+this.adresa.getObec()+":"+this.vek+":"+this.tel_cislo+":"+this.email;
		return s;
	}
	
	void upravPo(Scanner scan) {
		System.out.println("Zadajte novu poistovnu");
		String nov_p = scan.nextLine();
		System.out.println("Je nazov "+nov_p+" spravny? A/N");
		if(scan.nextLine().toUpperCase().charAt(0)=='A') this.poistovna = nov_p;
		else this.upravOsUd();
	}
	public void upravOsUd() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Zadajte co chcete zmenit: telefon (T), email: (E), poistovnu (P) koniec (K)");
		char c = scan.nextLine().toUpperCase().charAt(0);
		switch (c) {
		case 'T': 
			this.upravTel(scan);
			break;
		case 'K': 
			scan.close();
			return; 
		case 'E': 
			this.upravE(scan);
			break;
		case 'P': 
			this.upravPo(scan);
			break;
			
		}
		scan.close();
	}
	
	
	public void vypisPacTerminy() {
		for(Termin term: terminy) {
			System.out.println(term.toString());
		}
	}

	public String getMeno_zubara() {
		return meno_zubara;
	}

	public void setMeno_zubara(String meno_zubara) {
		this.meno_zubara = meno_zubara;
	}

	public String getRodne_cislo() {
		return rodne_cislo;
	}

	public void setRodne_cislo(String rodne_cislo) {
		this.rodne_cislo = rodne_cislo;
	}

	public String getPoistovna() {
		return poistovna;
	}

	public void setPoistovna(String poistovna) {
		this.poistovna = poistovna;
	}
	
}
