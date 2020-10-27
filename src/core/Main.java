package core;

import java.io.FileNotFoundException;
import java.util.Scanner;

import databaza.ID_typ;
import databaza.Reader;
import pouzivatelia.Majitel;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Prosim prihlaste sa");
		Scanner scan = new Scanner(System.in);
		System.out.println("Meno:");
		String meno = scan.nextLine();
		System.out.println("Heslo:");
		String heslo = scan.nextLine();
		ID_typ id_typ = Reader.najdiUzivatela(meno, heslo);
		switch(id_typ.getRola()) {
		case 'M':
			Majitel m = Reader.nacitajMajitela(meno, id_typ.getId());
			System.out.println("Nacital som");
		}

	}

}
