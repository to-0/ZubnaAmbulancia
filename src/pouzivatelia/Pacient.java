package pouzivatelia;
import java.util.ArrayList;
import java.util.Scanner;

import databaza.Reader;
import databaza.Writer;
import model.Termin;

public class Pacient extends Pouzivatel {
	private String meno_zubara;
	private String rodne_cislo;
	private String poistovna;
	ArrayList<Termin> terminy = new ArrayList<Termin>();
	ArrayList<Termin> volne_terminy = new ArrayList<Termin>();
	public Pacient(String meno,String priezvisko,String ulica,int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ) {
		super(meno, priezvisko,ulica,cislo_domu,obec, vek, telefon, email, id, typ);
		this.terminy = Reader.nacitajTerminy(this.priezvisko,this.id_typ);
		this.poistovna = "Nezadane";
		this.rodne_cislo = "Nezadane";
		this.meno_zubara= "Nezadane";
		this.volne_terminy = Reader.nacitajVolneTerm(this.meno_zubara);
	}
	public Pacient(String meno,String priezvisko,String ulica,int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ,
			String rodne_cislo, String meno_z,String poistovna){
		super(meno, priezvisko,ulica,cislo_domu,obec, vek, telefon, email, id, typ);
		this.terminy = Reader.nacitajTerminy(this.priezvisko,this.id_typ);
		this.poistovna = poistovna;
		this.rodne_cislo = rodne_cislo;
		this.meno_zubara= meno_z;
		this.volne_terminy = Reader.nacitajVolneTerm(this.meno_zubara);
	}
	public String toString() {
		String s= "Meno pacienta: "+this.meno+" "+this.priezvisko+"\nVek: " + this.vek+"\nTelefonne cislo: "
				+this.tel_cislo + "\nEmail: "+this.email +"\nRodne cislo: "+this.rodne_cislo+"\nPoistovna: "+this.poistovna
				+"\nMeno osetrujuceho zubara: "+this.meno_zubara;
		return s;
	}
	public void vypisVolneTerm() {
		int count=1;
		System.out.println(this.volne_terminy.size());
		for(Termin t: this.volne_terminy) {
			System.out.print("("+count+") ");
			System.out.println(t.toString());
			count++;
		}
	}
	public String toWriter() {
		String s = this.id_typ.getId()+":"+this.id_typ.getTyp()+":"+this.meno+":"+this.priezvisko+":"+this.adresa.getUlica()+":"+
	this.adresa.getCislo_domu()+":"+this.adresa.getObec()+":"+this.vek+":"+this.tel_cislo+":"+this.email+":"+this.rodne_cislo+":"+
				this.meno_zubara+":"+this.poistovna;
		return s;
	}
	 public boolean rezVolTerm(Scanner scan) {
		 this.vypisVolneTerm();
		 System.out.println("Napiste riadok cislo terminu");
		 int r = scan.nextInt();
		 r--;
		 if(r<0 && r>this.volne_terminy.size()-1) {
			 System.out.print("Neplatny riadok");
			 return false;
		 }
		 this.terminy.add(this.volne_terminy.get(r));
		 String riadok = this.volne_terminy.get(r).toWriter()+":"+this.meno_zubara;
		 Writer.vymazRiadok(Writer.vol_term_cesta,riadok);
		 this.volne_terminy.remove(r);
		 
		 return true;
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
}
