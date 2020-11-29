package pouzivatelia;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import databaza.Reader;
import databaza.Writer;

public class Majitel extends Zamestnanec {
	private ArrayList<Zamestnanec> zamestnanci = new ArrayList<Zamestnanec>();
		public Majitel(String meno,String priezvisko,String ulica,int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ) {
		super(meno, priezvisko,ulica,cislo_domu,obec, vek, telefon, email, id, typ);
	}
	public void vypisZamestnancov() {
		for(Zamestnanec z: this.zamestnanci) {
			if(z instanceof Zubar) {
				System.out.println("Pozicia: Zubar");
			}
			if(z instanceof Sestricka) System.out.println("Pozicia: Sestricka");
			if(z instanceof Recepcna) System.out.println("Pozicia: Pracovnik na recepcii");
			System.out.println(z.toString());
			System.out.println();
		}
	}
	public void pridajZamestnanca(Scanner scan) {
		System.out.println("Idete pridavat zubara, recepcneho pracovnika alebo sestricku? Napiste (Z/R/S)");
		String typ = scan.next();
		scan.nextLine();
		char c = typ.charAt(0);
		while(c!='Z' && c!='R' && c!='S' && c!='E') {
			System.out.println("Neplatny vstup, prosim zvolte Z/R/S alebo ak chcete ukoncit pridavanie zamestnanca E");
			typ = scan.nextLine();
		} 
		try {
			System.out.println("Meno:");
			String meno = scan.nextLine();
			System.out.println("Priezvisko:");
			String priezvisko = scan.nextLine();
			System.out.println("Obec:");
			String obec = scan.nextLine();
			System.out.println("Ulica:");
			String ulica= scan.nextLine();
			System.out.println("Cislo domu:");
			int cislo_domu= scan.nextInt();
			scan.nextLine();
			System.out.println("Telefonne cislo:");
			String cislo = scan.nextLine();
			System.out.println("Vek:");
			int vek;
			vek = scan.nextInt();
			scan.nextLine();
			System.out.println("Email:");
			String email = scan.nextLine();
			System.out.println("Prihlasovacie meno:");
			String nick = scan.nextLine();
			System.out.println("Heslo:");
			String heslo = scan.nextLine();
			switch(typ.charAt(0)) {
			case 'Z':
				Zubar z = new Zubar(meno, priezvisko,ulica,cislo_domu,obec, vek, cislo, email,this.getPocetPo()+1,'Z');
				this.setPocetPo(this.getPocetPo()+1);
				z.pridajPrihl_udaje(nick, heslo);
				if(Writer.pridajRiadok(Writer.uzivatelia_cesta,z.toWriter())) {
					String riadok = (this.getPocetPo())+":"+z.getPrihlasUdaje().toWriter()+":Z";
					Writer.pridajRiadok(Writer.prihlasovania_cesta, riadok);
				}
				
				zamestnanci.add(z);
				break;
			case 'R':
				Recepcna rec = new Recepcna(meno, priezvisko,ulica,cislo_domu,obec, vek, cislo, email,this.getPocetPo()+1,'R');
				zamestnanci.add(rec);
				rec.pridajPrihl_udaje(nick, heslo);
				this.setPocetPo(this.getPocetPo()+1);
				if(Writer.pridajRiadok(Writer.uzivatelia_cesta,rec.toWriter())) {
					String riadok = (this.getPocetPo())+":"+rec.getPrihlasUdaje().toWriter()+":R";
					Writer.pridajRiadok(Writer.prihlasovania_cesta, riadok);
				}
				break;
			case 'S':
				Sestricka s = new Sestricka(meno, priezvisko,ulica,cislo_domu,obec, vek, cislo, email,this.getPocetPo()+1,'S');
				zamestnanci.add(s);
				s.pridajPrihl_udaje(nick, heslo);
				this.setPocetPo(this.getPocetPo()+1);
				if(Writer.pridajRiadok(Writer.uzivatelia_cesta,s.toWriter())) {
					String riadok = (this.getPocetPo())+":"+s.getPrihlasUdaje().toWriter()+":R";
					Writer.pridajRiadok(Writer.prihlasovania_cesta, riadok);
				}
			}
		}
		catch (InputMismatchException ime) {
			System.out.println("Zly input");
			return;
		}
		
	}
	public void odstranZamestnanca(int id) {
		for(Zamestnanec z: this.zamestnanci) {
			if(z.getId_typ().getId()==id) {
				if(Writer.vymazRiadok(Writer.uzivatelia_cesta, z.toWriter())) {
					String riadok = Reader.najdiPrihlUd(id);
					System.out.println("tento riadok: "+riadok);
					Writer.vymazRiadok(Writer.prihlasovania_cesta, riadok);
				}
				return;	
			}
		}
	}
	public void setZamestnanci(ArrayList<Zamestnanec> zoznam) {
		this.zamestnanci = zoznam;
	}

}
