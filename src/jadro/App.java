package jadro;

import java.util.Scanner;

import databaza.Reader;
import model.ID_typ;
import pouzivatelia.Pouzivatel;
import pouzivatelia.Majitel;
import pouzivatelia.Recepcna;
import pouzivatelia.Sestricka;
import pouzivatelia.Zubar;

public class App {
	Pouzivatel pouzivatel;
	public static void main(String[] args) {
		App a = new App();
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
		switch(id_typ.getTyp()) {
		case 'M':
			this.pouzivatel= (Majitel)  Reader.nacitajMajitela(meno, id_typ.getId(),id_typ.getTyp(),heslo);
			if(this.pouzivatel==null) return false;
			System.out.println("Pred volanim akcii");
			this.akcie_majitel((Majitel) this.pouzivatel, scan);
			break;
		case 'Z':
			this.pouzivatel= (Zubar) Reader.nacitajZamestnanca(meno, id_typ.getTyp(), id_typ.getTyp(),heslo);
			if(this.pouzivatel==null) return false;
			break;
		case 'S':
			this.pouzivatel= (Sestricka) Reader.nacitajZamestnanca(meno, id_typ.getId(), id_typ.getTyp(),heslo);
			if(this.pouzivatel==null) return false;
			break;
		case 'R':
			this.pouzivatel= (Recepcna) Reader.nacitajZamestnanca(meno, id_typ.getId(), id_typ.getTyp(),heslo);
			if(this.pouzivatel==null) return false;
			break;
		}
		scan.close();
		return false;
	}
	public void akcie_majitel(Majitel m,Scanner scan) {
		//char c = scan.next().charAt(0);
		String prikaz = scan.next();
		while(!prikaz.equals("k")||!prikaz.equals("koniec")) {
			System.out.println("Vypis zamestnancov -v, pridaj zamestnanca -p, vypis osobne inf -o");
			switch(prikaz) {
			case "v":
				m.vypisZamestnancov();
				break;
			case "pz":
				m.pridajZamestnanca();
				break;
			case "o":
				m.vypisOsInf();
			}
			prikaz = scan.next();
			
		}
		System.out.println("Program ukonceny");
	}

}
