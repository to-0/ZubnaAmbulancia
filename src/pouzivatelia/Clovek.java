package pouzivatelia;

import java.util.Scanner;

import model.Adresa;
import model.ID_typ;

public class Clovek {
	private ID_typ id_typ;
	private String meno;
	private String priezvisko;
	private int vek;
	private String tel_cislo;
	private String email;
	private PrihlasovacieUdaje prihlas_udaje;
	private Adresa adresa;
	public Clovek(String meno,String priezvisko,String ulica,int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ) {
		this.setMeno(meno);
		this.adresa = new Adresa(ulica,cislo_domu,obec);
		this.setPriezvisko(priezvisko);
		this.vek = vek;
		this.setTel_cislo(telefon);
		this.setEmail(email);
		this.setId_typ(new ID_typ(id, typ));
		this.prihlas_udaje = new PrihlasovacieUdaje();
		
	}
	public String toString() {
		String s= "Meno pacienta: "+this.getMeno()+" "+this.getPriezvisko()+"\nVek: " + this.getVek()+"\nTelefonne cislo:"
				+this.getTel_cislo() + "\n Email: "+this.getEmail();
		return s;
	}
	public void vypisOsInf() {
		System.out.println(this.toString());
	}
	void upravTel(Scanner scan) {
		System.out.println("zadajte nove telefonne cislo");
		String nov_tc = scan.nextLine();
		System.out.println("Chcete toto "+nov_tc+" cislo pridat? A/N");
		if(scan.nextLine().toUpperCase().charAt(0)=='A') this.tel_cislo = nov_tc;
		else this.upravOsUd();
		
	}
	void upravE(Scanner scan) {
		System.out.println("Zadajte nove telefonne cislo");
		String nov_e = scan.nextLine();
		System.out.println("Chcete tento "+nov_e+" email? A/N");
		if(scan.nextLine().toUpperCase().charAt(0)=='A') this.email = nov_e;
		else this.upravOsUd();
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
		else this.upravOsUd();
	}
	public void upravOsUd() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Zadajte co chcete zmenit: telefon (T), email (E), adresu (A), koniec (K)?");
		char c = scan.nextLine().toUpperCase().charAt(0);
		switch (c) {
		case 'T': 
			this.upravTel(scan);
			break;
		case 'K': return; 
		case 'E': this.upravE(scan);
		case 'A': this.upravA(scan);
			
		}
	}
	public void pridajPrihl_udaje(String nick, String heslo) {
		this.prihlas_udaje.setMeno(meno);
		this.prihlas_udaje.setHeslo(heslo);
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
				/*tu budem musiet prepisat ten subor cely.... prihlasovania.txt a vynechat
				ten riadok s pouzivatelom a napisat ho asi nakoniec s rovnakym id ale
				inym heslom*/
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
	public void setId_typ(ID_typ id_typ) {
		this.id_typ = id_typ;
	}
	public String getMeno() {
		return meno;
	}
	public void setMeno(String meno) {
		this.meno = meno;
	}
	public String getPriezvisko() {
		return priezvisko;
	}
	public void setPriezvisko(String priezvisko) {
		this.priezvisko = priezvisko;
	}
	public int getVek() {
		return vek;
	}
	public String getTel_cislo() {
		return tel_cislo;
	}
	public void setTel_cislo(String tel_cislo) {
		this.tel_cislo = tel_cislo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
