package pouzivatelia;

import java.util.Scanner;

import databaza.Writer;

public class Recepcna extends Zamestnanec {
	public Recepcna(String meno,String priezvisko,String ulica, int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ) {
		super(meno, priezvisko,ulica,cislo_domu, obec, vek, telefon, email, id, typ);
		// TODO Auto-generated constructor stub
	}
	public boolean pridajPacienta(Scanner scan) {
		int id = this.pocetPo+1;
		this.pocetPo++;
		System.out.println("Meno:");
		String krstne = scan.next();
		System.out.println("Priezvisko:");
		String priezvisko = scan.next();
		System.out.println("Ulica:");
		String ulica = scan.next();
		System.out.println("Cislo domu:");
		int cislo = scan.nextInt();
		System.out.println("Obec:");
		String obec = scan.next();
		System.out.println("Vek:");
		int vek = scan.nextInt();
		System.out.println("Telefonne cislo:");
		String tel = scan.next();
		System.out.println("Email:");
		String email = scan.next();
		System.out.println("Prihlasovacie meno:");
		String nick= scan.next();
		System.out.println("Heslo:");
		String heslo = scan.next();
		System.out.println("Rodne cislo: ");
		String rodne_c = scan.next();
		System.out.println("Poistovna: ");
		String poistovna = scan.next();
		System.out.println("Meno osetrujuceho zubara: ");
		String zubar = scan.next();
		
		
		Pacient p = new Pacient(krstne,priezvisko,ulica,cislo,obec,vek,tel,email,id,'P',rodne_c,zubar,poistovna);
		p.pridajPrihl_udaje(nick, heslo);
		if(p==null) return false;
		this.pacienti.add(p);
		Writer.zapisPouzivatela(p);
		return true;
	}
	//TODO
	public  boolean odstranPacienta(int id) {
		Pacient temp;
		int i=0;
		int index=-1;
		for(Pacient p: this.pacienti) {
			if(p.id_typ.getId()==id) {
				temp = p;
				index = i;
				break;
			}
			i++;
		}
		Writer.vymazPac(this.pacienti.get(index));
		if(index!=-1)
			this.pacienti.remove(this.pacienti.get(index));
		else return false;
		
		return true;
	}
	

}
