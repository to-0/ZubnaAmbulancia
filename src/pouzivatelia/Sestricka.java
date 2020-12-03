package pouzivatelia;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import databaza.Writer;
import terminy.VolnyTermin;

public class Sestricka extends Zamestnanec {

	public Sestricka(String meno,String priezvisko,String ulica,int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ) {
		super(meno, priezvisko, ulica, cislo_domu, obec, vek, telefon, email, id, typ);
			
	}
	public void pridajTerm(Scanner scan) {
		try {
		System.out.println("Pridajte termin vo formate den"
					+ ":mesiac:rok:hodina:minuta:zubar");
		String c= scan.next();
		scan.nextLine();
		String []riadok = c.split(":");
		System.out.println(c);
		if(riadok.length<6) {
			//System.out.println(Arrays.toString(riadok));
			//System.out.println("Zly format");
			System.out.print(riadok.length);
			return;
		}
		VolnyTermin t = new VolnyTermin(Integer.parseInt(riadok[0]),Integer.parseInt(riadok[1]),Integer.parseInt(riadok[2]),Integer.parseInt(riadok[3]),Integer.parseInt(riadok[4]),riadok[5]);
		if(Writer.pridajRiadok(Writer.vol_term_cesta,t.toWriter())) System.out.println("Termin pridany");
		else System.out.println("Nieco sa pokazilo");
		}
		catch (InputMismatchException ime) {
			System.out.println("Zly input");
			return;
		}
	}
}
