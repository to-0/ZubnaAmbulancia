package databaza;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pouzivatelia.Majitel;
import pouzivatelia.Zubar;

public class Reader { //cita subory, vytvara objekty ked sa prihlasi pouzivatel, overuje prihlasenie

	String cesta;
	public Reader(String src) {
		this.cesta = src;
	}
	public Reader() {
		this.cesta="/";
	}
	public static ID_typ najdiUzivatela(String meno, String heslo) throws FileNotFoundException { //hladam pouzivatela ci existuje, ci sa rovna heslo a vratim id
		File subor = new File("src/databaza/prihlasovania.txt");
		try (Scanner scan = new Scanner(subor)) {
			while(scan.hasNextLine()) {
				String riadok = scan.nextLine();
				System.out.println(riadok);
				String[] casti = riadok.split("$");
				System.out.println(casti.toString());
				if(meno==casti[1] && heslo==casti[2])
					return new ID_typ(Integer.parseInt(casti[0]),casti[3].charAt(0));
			}
		}
		return null; //ak neexistuje 
	}
	public static Majitel nacitajMajitela(String meno,int id) {
		try (Scanner scan = new Scanner("majitelia.txt")) {
			while(scan.hasNextLine()) {
				String riadok = scan.nextLine();
				String[] casti = riadok.split("$");
				if(id==Integer.parseInt(casti[0]) && meno==casti[2] && "M"==casti[1]) {
					//id$typ$meno$priezvisko$vek
					String priezvisko = casti[2];
					int vek = Integer.parseInt(casti[3]);
					if(casti.length!=5) return null;
					Majitel m = new Majitel(meno, priezvisko, vek, casti[4],casti[5]);
					return m;
				}
			}
		}
		return null; //ak nenajdem majitela
	}
}
