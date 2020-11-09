package jadro;

import java.util.Scanner;

import databaza.Reader;
import model.ID_typ;
import pouzivatelia.Majitel;

public class Main {

	public static void main(String[] args) {
		System.out.println("Prosim prihlaste sa");
		Scanner scan = new Scanner(System.in);
		System.out.println("Meno:");
		String meno = scan.nextLine();
		System.out.println("Heslo:");
		String heslo = scan.nextLine();
		ID_typ id_typ = Reader.najdiUzivatela(meno, heslo);
		switch(id_typ.getTyp()) {
		case 'M':
			Majitel m = Reader.nacitajMajitela(meno, id_typ.id,id_typ.typ,heslo);
			System.out.println("Nacital som");
			if(m==null) {
				scan.close();
				return;
			}
			m.pridajPrihl_udaje(meno, heslo);
			//m.pridajZamestnanca();
			m.vypisZamestnancov();
		}
		scan.close();

	}

}
