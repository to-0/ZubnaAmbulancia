package pouzivatelia;
import java.util.ArrayList;
import java.util.Scanner;

import model.Clovek;

public class Majitel extends Zamestnanci {
	ArrayList<Zubar> zubari = new ArrayList<Zubar>();
	ArrayList<Sestricka> sestricky = new ArrayList<Sestricka>();
	ArrayList<Recepcna> recepcia = new ArrayList<Recepcna>();

	public Majitel(String firstn, String secondN, int age, String phone_num,String email) {
		super(firstn, secondN, age, phone_num, email);
		// TODO Auto-generated constructor stub
	}
	void vypisZamestnancov() {
		System.out.println("Zubari");
		for(Zubar zub: zubari) {
			System.out.println(zub.meno + " "+zub.priezvisko);
		}
		System.out.println("Sestricky");
		for(Sestricka ses: sestricky) {
			System.out.println(ses.meno + " "+ses.priezvisko);
		}
		System.out.println("Recepcia");
		for(Recepcna rec: recepcia) {
			System.out.println(rec.meno + " "+rec.priezvisko);
		}
	}
	public void pridajZamestnanca() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Idete pridavat zubara, recepcneho pracovnika alebo sestricku? Napiste (Z/R/S)");
		String typ = scan.nextLine();
		while(typ!="Z"||typ!="R" ||typ!="S" ||typ!="E") {
			System.out.println("Neplatny vstup, prosim zvolte Z/R/S alebo ak chcete ukoncit pridavanie zamestnanca E");
			typ = scan.nextLine();
		} 
		System.out.println("Meno:");
		String meno = scan.nextLine();
		System.out.println("Priezvisko:");
		String priezvisko = scan.nextLine();
		System.out.println("Vek");
		int vek = Integer.parseInt(scan.nextLine());
		System.out.println("Telefonne cislo:");
		String cislo = scan.nextLine();
		System.out.println("Email:");
		String email = scan.nextLine();
		
		switch(typ) {
		case "Z":
			Zubar z = new Zubar(meno, priezvisko, vek, cislo, email);
			zubari.add(z);
			break;
		case "R":
			Recepcna rec = new Recepcna(meno, priezvisko, vek, cislo, email);
			recepcia.add(rec);
			break;
		case "S":
			Sestricka s = new Sestricka(meno, priezvisko, vek, cislo, email);
			sestricky.add(s);
		}
		
	}
	

}
