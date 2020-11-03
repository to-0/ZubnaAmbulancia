package databaza;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.ID_typ;
import model.Termin;

import java.util.ArrayList;
import java.util.Arrays;

import pouzivatelia.Majitel;
import pouzivatelia.Pacient;
import pouzivatelia.Recepcna;
import pouzivatelia.Sestricka;
import pouzivatelia.Zamestnanec;
import pouzivatelia.Zubar;

public class Reader { //cita subory, vytvara objekty ked sa prihlasi pouzivatel, overuje prihlasenie

	static String prihlasovania_cesta = System.getProperty("user.dir")+"\\src\\databaza\\prihlasovania.txt";
	static String uzivatelia_cesta =  System.getProperty("user.dir")+"\\src\\databaza\\uzivatelia.txt";
	static String rez_term_cesta = System.getProperty("user.dir")+"\\src\\databaza\\rezervovane_terminy.txt";
	
	public static ID_typ najdiUzivatela(String meno, String heslo) { //hladam pouzivatela ci existuje, ci sa rovna heslo a vratim id
		File subor = new File(prihlasovania_cesta);
		try (Scanner scan = new Scanner(subor)) {
			while(scan.hasNextLine()) {
				String riadok = scan.nextLine();
				System.out.println(riadok);
				String[] casti = riadok.split(":");
				System.out.println(Arrays.toString(casti));
				if(meno.equals(casti[1]) && heslo.equals(casti[2])) {
					System.out.println("Rovna sa");
					return new ID_typ(Integer.parseInt(casti[0]),casti[3].charAt(0));
				}
					
			}
			return null; //ak neexistuje 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Majitel nacitajMajitela(String nick,int id,char typ,String heslo) {
		File subor = new File(uzivatelia_cesta);
		Majitel m = null;
		ArrayList<Zubar> zubari = new ArrayList<Zubar>();
		ArrayList<Sestricka> sestricky = new ArrayList<Sestricka>();
		ArrayList<Recepcna> recepcne = new ArrayList<Recepcna>();
		ArrayList<Pacient> pacienti = new ArrayList<Pacient>();
		System.out.println("Idem skusit otvorit subor");
		try (Scanner scan = new Scanner(subor)) {
			System.out.println("Otvoril som");
			System.out.println("ID "+id);
			while(scan.hasNextLine()) {
				String riadok = scan.nextLine();
				String[] casti = riadok.split(":");
				String krstne_meno = casti[2];
				String priezvisko = casti[3];
				String ulica = casti[4];
				int cislo_domu = Integer.parseInt(casti[5]);
				String obec = casti[6];
				String cislo = casti[8];
				String email = casti[9];
				int vek = Integer.parseInt(casti[7]);
				//if(casti.length!=6) continue; //precital som bullshit
				if("Z".equals(casti[1])) zubari.add(new Zubar(krstne_meno,priezvisko,ulica,cislo_domu,obec,vek,cislo,email,Integer.parseInt(casti[0]),'Z'));
				if("S".equals(casti[1])) sestricky.add(new Sestricka(krstne_meno,priezvisko,ulica,cislo_domu,obec,vek,cislo,email,Integer.parseInt(casti[0]),'S'));
				if("R".equals(casti[1])) recepcne.add(new Recepcna(krstne_meno,priezvisko,ulica,cislo_domu,obec,vek,cislo,email,Integer.parseInt(casti[0]),'R'));
				if("P".equals(casti[1])) pacienti.add(new Pacient(krstne_meno,priezvisko,ulica,cislo_domu,obec,vek,cislo,email,Integer.parseInt(casti[0]),'P'));
				
				System.out.println("TU TU TUTU "+casti[0]);
				int nac_id = Integer.parseInt(casti[0]);
				if(id==nac_id) {
					System.out.println("Nasiel som ho");
					m = new Majitel(krstne_meno, priezvisko,ulica,cislo_domu,obec, vek, cislo,email,id,typ);
					m.pridajPrihl_udaje(nick, heslo);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Nenasiel sa subor");
			e.printStackTrace();
		}
		if(m!=null) {
			m.setZubari(zubari);
			m.setRecepcia(recepcne);
			m.setSestricky(sestricky);
			m.setPacienti(pacienti);
			return m;
		}
		return null; //ak nenajdem majitela
	}
	public static Pacient nacitajPacienta(String nick, int id,char typ,String heslo) {
		ArrayList<Termin> terminy = new ArrayList<Termin>();
		File subor = new File(uzivatelia_cesta);
		try (Scanner scan = new Scanner(subor)) {
			while(scan.hasNextLine()) {
				
				String riadok = scan.nextLine();
				String[] casti = riadok.split(":");
				String krstne_meno = casti[2];
				String priezvisko = casti[3];
				String ulica = casti[4];
				int cislo_domu = Integer.parseInt(casti[5]);
				String obec = casti[6];
				int vek = Integer.parseInt(casti[7]);
				String cislo = casti[8];
				String email = casti[9];
				
				//nasiel som daneho pacienta nacitam udaje o nom 
				if(id==Integer.parseInt(casti[0])) {
					Pacient p = new Pacient(krstne_meno, priezvisko,ulica,cislo_domu,obec, vek, cislo,email,id,typ);
					
					p.pridajPrihl_udaje(nick, heslo);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Nenasiel sa subor");
			e.printStackTrace();
		}
		return null;
	}
	public static ArrayList<Termin> nacitajTerminy(int id){
		return null;
		
	}
	
	public static Zamestnanec nacitajZamestnanca(String nick, int id,char typ,String heslo) {
		ArrayList<Pacient> pacienti  = new ArrayList<Pacient>();
		Zamestnanec zam = null;
		File subor = new File(uzivatelia_cesta);
		try (Scanner scan = new Scanner(subor)) {
			while(scan.hasNextLine()) {
				String riadok = scan.nextLine();
				String[] casti = riadok.split(":");
				String krstne_meno = casti[2];
				String priezvisko = casti[3];
				String ulica = casti[4];
				int cislo_domu = Integer.parseInt(casti[5]);
				String obec = casti[6];
				int vek = Integer.parseInt(casti[7]);
				String cislo = casti[8];
				String email = casti[9];
				if(casti.length!=6) continue; //precital som bullshit
				if("P".equals(casti[1])) pacienti.add(new Pacient(krstne_meno,priezvisko,ulica,cislo_domu,obec,vek,cislo,email,Integer.parseInt(casti[0]),'P'));
				
				//nasiel som daneho zamestnanca nacitam udaje o nom 
				if(id==Integer.parseInt(casti[0])) {
					zam = new Zamestnanec(krstne_meno, priezvisko,ulica,cislo_domu,obec, vek, cislo,email,id,typ);
					zam.pridajPrihl_udaje(nick, heslo);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Nenasiel sa subor");
			e.printStackTrace();
		}
		if(zam!=null) {
			zam.setPacienti(pacienti);
			return zam;
		}
		return null;
	}
}
