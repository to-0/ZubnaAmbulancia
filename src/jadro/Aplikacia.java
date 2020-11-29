package jadro;

import java.util.InputMismatchException;
import java.util.Scanner;

import databaza.Reader;
import model.ID_typ;
import pouzivatelia.Pouzivatel;
import pouzivatelia.Majitel;
import pouzivatelia.Pacient;
import pouzivatelia.Recepcna;
import pouzivatelia.Sestricka;
import pouzivatelia.Zamestnanec;
import pouzivatelia.Zubar;

public class Aplikacia {
	Pouzivatel pouzivatel;
	public static void main(String[] args) {
		Aplikacia a = new Aplikacia();
		a.start();
		
	}
	public boolean start() {
		System.out.println("Prosim prihlaste sa");
		Scanner scan = new Scanner(System.in);
		System.out.println("Meno:");
		String meno = scan.nextLine();
		System.out.println("Heslo:");
		String heslo = scan.nextLine();
		ID_typ id_typ = Reader.najdiUzivatela(meno, heslo);
		if(id_typ==null) this.start();
		switch(id_typ.getTyp()) {
		case 'M':
			this.pouzivatel= (Majitel) Reader.nacitajMajitela(meno, id_typ.getId(),id_typ.getTyp(),heslo);
			if(this.pouzivatel==null) {
				System.out.println("Nenasiel");
				return false;
			}
			this.akcie(scan);
			break;
		case 'Z':
			this.pouzivatel= (Zubar) Reader.nacitajZamestnanca(meno, id_typ.getId(), id_typ.getTyp(),heslo);
			if(this.pouzivatel==null) {
				System.out.println("Nenasiel");
				return false;
			}
			this.akcie(scan);
			break;
		case 'S':
			this.pouzivatel= (Sestricka) Reader.nacitajZamestnanca(meno, id_typ.getId(), id_typ.getTyp(),heslo);
			if(this.pouzivatel==null) return false;
			this.akcie(scan);
			break;
		case 'R':
			this.pouzivatel= (Recepcna) Reader.nacitajZamestnanca(meno, id_typ.getId(), id_typ.getTyp(),heslo);
			if(this.pouzivatel==null) {
				System.out.println("Nenasiel");
				return false;
			}
			this.akcie(scan);
			break;
		case 'P':
			this.pouzivatel = (Pacient) Reader.nacitajPacienta(meno, id_typ.getId(), id_typ.getTyp(), heslo);
			if(this.pouzivatel==null) {
				System.out.println("Nenasiel");
				return false;
			}
			this.akcie(scan);
		}
		
		scan.close();
		return false;
	}
	
	public void akcie(Scanner scan) {
		String prikaz = scan.next();
		while(!prikaz.equals("koniec")) {
			switch(prikaz) {
			case "odhlas":
				this.pouzivatel=null;
				this.start();
				break;
			case "vypisZam":
				if(this.pouzivatel instanceof Majitel) {
					((Majitel) this.pouzivatel).vypisZamestnancov();
				}
				break;
			case "vypisOsInf":
				this.pouzivatel.vypisOsInf();
				break;
			case "pridajZam":
				if(this.pouzivatel instanceof Majitel) {
					((Majitel) this.pouzivatel).pridajZamestnanca(scan);
				}
				break;
			case "upravOsUd":
				this.pouzivatel.upravOsUd(scan);
				break;
			case "zmenHeslo":
				this.pouzivatel.zmenHeslo(scan);
				break;
			case "vypisPac": //vypis vsetkych pacientov
				if(this.pouzivatel instanceof Zamestnanec) {
					((Zamestnanec) this.pouzivatel).vypisPacientov();
				}
				break;
			case "vypisMoPac": //vypis pacientov daneho zubara iba
				if(this.pouzivatel instanceof Zubar) {
					((Zubar) this.pouzivatel).vypisZubPacientov();
				}
				break;
			case "zobrazPac":
				if(this.pouzivatel instanceof Zamestnanec) {
					try { //skusim ci zada integer a zavolam ked tak zobraz pacienta podla id
					int id = scan.nextInt();
					((Zamestnanec) this.pouzivatel).zobrazPacienta(id);
					}
					catch (InputMismatchException ime) { //ak nie zadal string a podla toho...
						String priezvisko = scan.next();
						((Zamestnanec) this.pouzivatel).ZobrazPacienta(priezvisko);
					}
				}
				break;
			case "rezTerm": //rezervuj si termin
				if(this.pouzivatel instanceof Pacient) {
					((Pacient) this.pouzivatel).rezVolTerm(scan);
				}
				break;
			case "vypisTerm":
				if(this.pouzivatel instanceof Pacient) {
					((Pacient) this.pouzivatel).vypisPacTerminy();
				}
				break;
			case "pridajPacienta":
				if(this.pouzivatel instanceof Recepcna) {
					((Recepcna) this.pouzivatel).pridajPacienta(scan);
				}
			case "odstranPacienta":
				if(this.pouzivatel instanceof Recepcna) {
					System.out.println("Id pacienta");
					int id = scan.nextInt();
					((Recepcna) this.pouzivatel).odstranPacienta(id);
				}
				break;
			case "odstranZamestnanca":
				if(this.pouzivatel instanceof Majitel) {
					System.out.println("Id zamestnanca");
					int id = scan.nextInt();
					((Majitel) this.pouzivatel).odstranZamestnanca(id);
				}
				break;
			case "pridajTerm":
				if(this.pouzivatel instanceof Sestricka) {
					((Sestricka) this.pouzivatel).pridajTerm(scan);
				}
				break;
		}
			prikaz= scan.nextLine();
		}
		System.out.println("Program skoncil");
	}
	
}
