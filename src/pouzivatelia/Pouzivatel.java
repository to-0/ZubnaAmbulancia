package pouzivatelia;

import java.util.Scanner;

import databaza.Writer;
import model.Adresa;
import model.ID_typ;
import model.PrihlasovacieUdaje;
import model.TermPouzInterface;

public class Pouzivatel implements TermPouzInterface{
	//tu som to mal najprv vsetko private ale nedavalo mi to logicky moc zmysel
	//pretoze samotnu class pouzivatel nemam inicializovanu iba sa z nej dedi
	//a vsetky childy su v rovnakom package takze som to nechal na default...
	//nechavam default aby pristup zostal iba v ramci balicka 
	ID_typ id_typ;
	 String meno;
	 String priezvisko;
	 int vek;
	 String tel_cislo;
	 String email;
	 PrihlasovacieUdaje prihlas_udaje;
	 Adresa adresa;
	public Pouzivatel(String meno,String priezvisko,String ulica,int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ) {
		this.meno = meno;
		this.adresa = new Adresa(ulica,cislo_domu,obec);
		this.priezvisko = priezvisko;
		this.vek = vek;
		this.tel_cislo = telefon;
		this.email = email;
		this.id_typ = new ID_typ(id, typ);
		this.prihlas_udaje = null;
	}
	public String toString() {
		//System.out.println("Pouz to string");
		String s= "Id: "+this.getId_typ().getId()+"\nMeno: "+this.meno+" "+this.priezvisko+"\nVek: " + this.vek+"\nTelefonne cislo:"
				+this.tel_cislo + "\nEmail: "+this.email + "\nAdresa: "+this.adresa.toString();
		return s;
	}
	public void vypisOsInf() {
		System.out.println(this.toString());
	}
	void upravTel(Scanner scan) {
		System.out.println("zadajte nove telefonne cislo");
		String nov_tc = scan.next();
		System.out.println("Chcete toto "+nov_tc+" cislo pridat? A/N");
		if(scan.next().toUpperCase().charAt(0)=='A') this.tel_cislo = nov_tc;
		else this.upravOsUd(scan);
		System.out.println("Koncim T");
		
	}
	void upravE(Scanner scan) {
		System.out.println("Zadajte novy email");
		String nov_e = scan.nextLine();
		System.out.println("Chcete tento "+nov_e+" email? A/N");
		if(scan.nextLine().toUpperCase().charAt(0)=='A') this.email = nov_e;
		else this.upravOsUd(scan);
	}
	
	void upravA(Scanner scan) {
		System.out.println("Ulica:");
		String ulica = scan.nextLine();
		System.out.println("Cislo domu:");
		int cislo = scan.nextInt();
		System.out.println("Obec:");
		String obec = scan.nextLine();
		Adresa adresa = new Adresa(ulica,cislo,obec);
		System.out.println("Je tato adresa spravna? A/N"+adresa.toString());
		if(scan.nextLine().toUpperCase().charAt(0)=='A') this.adresa=adresa;
		else this.upravOsUd(scan);
		
	}
	public void upravOsUd(Scanner scan) {
		System.out.println("Zadajte co chcete zmenit: telefon (T), email (E), adresu (A), koniec (K)?");
		char c = scan.next().toUpperCase().charAt(0);
		switch (c) {
		case 'T': 
			this.upravTel(scan);
			return;
		case 'K': 
			return; 
		case 'E': this.upravE(scan);
			return;
		case 'A': this.upravA(scan);
				return;
		}
	}
	public void pridajPrihl_udaje(String nick, String heslo) {
		this.prihlas_udaje = new PrihlasovacieUdaje(nick,heslo);
	}
	public String toWriter() {
		String s = this.id_typ.getId()+":"+this.id_typ.getTyp()+":"+this.meno+":"+this.priezvisko+":"+this.adresa.getUlica()+":"+
	this.adresa.getCislo_domu()+":"+this.adresa.getObec()+":"+this.vek+":"+this.tel_cislo+":"+this.email;
		return s;
	}
	public void login() {
		System.out.println("Prihlasili ste sa ako "+this.meno + " "+this.priezvisko);
	}
	public void zmenHeslo(Scanner scan) {
		System.out.println("Zadajte stare heslo:");
		String stare_heslo = scan.nextLine();
		System.out.println("Zadajte nove heslo");
		String nove_heslo = scan.nextLine();
		if(stare_heslo.equals(this.prihlas_udaje.getHeslo())) {
			System.out.println("Ste si isty ze chcete zmenit heslo na: "+nove_heslo + " A/N");
			char c = scan.nextLine().toUpperCase().charAt(0);
			if(c=='A') {
				Writer.zmenHeslo(this, nove_heslo);
				this.prihlas_udaje.setHeslo(nove_heslo); 
				
			}
			else return;
		}
		else {
			System.out.println("Zle stare heslo");
			return;
		}
	}
	
	
	public ID_typ getId_typ() {
		return id_typ;
	}
	public String getMeno() {
		return meno;
	}
	public String getPriezvisko(){
		return this.priezvisko;
	}
	
	public PrihlasovacieUdaje getPrihlasUdaje() {
		return this.prihlas_udaje;
	}
}
