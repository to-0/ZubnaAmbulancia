package pouzivatelia;

import java.util.InputMismatchException;
import java.util.Scanner;

import databaza.Reader;
import databaza.Writer;

public class Recepcna extends Zamestnanec {
	public Recepcna(String meno,String priezvisko,String ulica, int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ) {
		super(meno, priezvisko,ulica,cislo_domu, obec, vek, telefon, email, id, typ);
	}
	public boolean pridajPacienta(Scanner scan) {
		int id = this.pocetPo; //idcka su od nuly cize pocet je vzdy ako keby o 1 vacsi... cize nedam +1 ale zvysim iba pocet o 1
		this.pocetPo++;
		try {
			scan.nextLine();
			System.out.println("Meno:");
			String krstne = scan.nextLine();
			System.out.println("Priezvisko:");
			String priezvisko = scan.nextLine();
			System.out.println("Ulica:");
			String ulica = scan.nextLine();
			System.out.println("Cislo domu:");
			int cislo = scan.nextInt();
			scan.nextLine();
			System.out.println("Obec:");
			String obec = scan.nextLine();
			System.out.println("Vek:");
			int vek = scan.nextInt();
			scan.nextLine();
			System.out.println("Telefonne cislo:");
			String tel = scan.nextLine();
			System.out.println("Email:");
			String email = scan.nextLine();
			System.out.println("Prihlasovacie meno:");
			String nick= scan.nextLine();
			System.out.println("Heslo:");
			String heslo = scan.nextLine();
			System.out.println("Rodne cislo: ");
			String rodne_c = scan.nextLine();
			System.out.println("Poistovna: ");
			String poistovna = scan.nextLine();
			System.out.println("Meno osetrujuceho zubara: ");
			String zubar = scan.nextLine();
			Pacient p = new Pacient(krstne,priezvisko,ulica,cislo,obec,vek,tel,email,id,'P',rodne_c,zubar,poistovna);
			p.pridajPrihl_udaje(nick, heslo);
			this.pacienti.add(p);
			if(Writer.pridajRiadok(Writer.uzivatelia_cesta,p.toWriter())) {
				String riadok = id+":"+p.getPrihlasUdaje().toWriter()+":R";
				Writer.pridajRiadok(Writer.prihlasovania_cesta, riadok);
			}
		} catch (InputMismatchException ime) {
			System.out.println("Zly input");
			return false;
		}
		return true;
	}
	public  boolean odstranPacienta(int id) {
		Pacient p=null;
		int i=0;
		int index=-1;
		for(Pacient pac: this.pacienti) {
			if(pac.id_typ.getId()==id) {
				p = pac;
				index = i;
				break;
			}
			i++;
		}
		if(p==null)return false;
		if(Writer.vymazRiadok(Writer.uzivatelia_cesta, p.toWriter())) {
			String riadok = Reader.najdiPrihlUd(id);
			System.out.println("riadok "+riadok);
			Writer.vymazRiadok(Writer.prihlasovania_cesta,riadok);
		}
		if(index!=-1)
			this.pacienti.remove(this.pacienti.get(index));
		else return false;
		
		return true;
	}
	

}
