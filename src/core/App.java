package core;

import java.util.Scanner;

import databaza.Reader;
import model.ID_typ;
import pouzivatelia.Clovek;
import pouzivatelia.Majitel;
import pouzivatelia.Recepcna;
import pouzivatelia.Sestricka;
import pouzivatelia.Zubar;

public class App {
	Clovek pouzivatel;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
			System.out.println("Nacital som");
			if(this.pouzivatel==null) return false;
			break;
		case 'Z':
			this.pouzivatel= (Zubar) Reader.nacitajZamestnanca(meno, id_typ.getId(), id_typ.getTyp(),heslo);
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
		return false;
	}

}
