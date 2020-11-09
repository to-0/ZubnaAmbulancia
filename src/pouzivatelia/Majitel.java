package pouzivatelia;
import java.util.ArrayList;
import java.util.Scanner;

public class Majitel extends Zamestnanec {
	private ArrayList<Zubar> zubari = new ArrayList<Zubar>();
	private ArrayList<Sestricka> sestricky = new ArrayList<Sestricka>();
	private ArrayList<Recepcna> recepcia = new ArrayList<Recepcna>();
	private int pocet_pouzivatelov; //toto pri nacitani majitela musim zistit asi
	public Majitel(String meno,String priezvisko,String ulica,int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ) {
		super(meno, priezvisko,ulica,cislo_domu,obec, vek, telefon, email, id, typ);
		// TODO Auto-generated constructor stub
	}
	public void vypisZamestnancov() {
		System.out.println("Zubari");
		for(Zubar zub: zubari) {
			System.out.println(zub.getMeno() + " "+zub.getPriezvisko());
		}
		System.out.println("Sestricky");
		for(Sestricka ses: sestricky) {
			System.out.println(ses.getMeno() + " "+ses.getPriezvisko());
		}
		System.out.println("Recepcia");
		for(Recepcna rec: recepcia) {
			System.out.println(rec.getMeno() + " "+rec.getPriezvisko());
		}
	}
	public void pridajZamestnanca() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Idete pridavat zubara, recepcneho pracovnika alebo sestricku? Napiste (Z/R/S)");
		String typ = scan.nextLine();
		char c = typ.charAt(0);
		while(c!='Z' && c!='R' && c!='S' && c!='E') {
			System.out.println("Neplatny vstup, prosim zvolte Z/R/S alebo ak chcete ukoncit pridavanie zamestnanca E");
			typ = scan.nextLine();
		} 
		System.out.println("Meno:");
		String meno = scan.nextLine();
		System.out.println("Priezvisko:");
		String priezvisko = scan.nextLine();
		System.out.println("Ulica:");
		String ulica= scan.nextLine();
		System.out.println("Cislo domu:");
		int cislo_domu= scan.nextInt();
		System.out.println("Obec:");
		String obec = scan.next();
		System.out.println("Vek:");
		int vek = scan.nextInt();
		System.out.println("Telefonne cislo:");
		String cislo = scan.next();
		System.out.println("Email:");
		String email = scan.nextLine();
		switch(typ.charAt(0)) {
		case 'Z':
			Zubar z = new Zubar(meno, priezvisko,ulica,cislo_domu,obec, vek, cislo, email,this.pocet_pouzivatelov+1,'Z');
			zubari.add(z);
			break;
		case 'R':
			Recepcna rec = new Recepcna(meno, priezvisko,ulica,cislo_domu,obec, vek, cislo, email,this.pocet_pouzivatelov+1,'R');
			recepcia.add(rec);
			break;
		case 'S':
			Sestricka s = new Sestricka(meno, priezvisko,ulica,cislo_domu,obec, vek, cislo, email,this.pocet_pouzivatelov+1,'S');
			sestricky.add(s);
		}
		
	}
	public void setZubari(ArrayList<Zubar> zoznam) {
		this.zubari = zoznam;
	}
	public void setRecepcia(ArrayList<Recepcna> zoznam) {
		this.recepcia = zoznam;
		
	}
	public void setSestricky(ArrayList<Sestricka> zoznam) {
		this.sestricky = zoznam;
	}
	public ArrayList<Sestricka> getSestricky(){
		return this.sestricky;
	}
	public ArrayList<Zubar> getZubari(){
		return this.zubari;
	}

	public ArrayList<Recepcna> getRecepcia(){
		return this.recepcia;
	}


}
