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
	static String volne_term_cesta = System.getProperty("user.dir")+"\\src\\databaza\\volne_terminy.txt";

	public static ArrayList<Termin> nacitajTerminy(String priezvisko,ID_typ id_typ){ //priezvisko a typ ci je zubar alebo pacient
		File f;
		f = new File(rez_term_cesta);
		Scanner scan;
		boolean nasiel = false;
		ArrayList<Termin> terminy = new ArrayList<Termin>();
		try {
			scan = new Scanner(f);
			while(scan.hasNextLine()) {
				nasiel = false;
				String riadok = scan.nextLine();
				String []arr = riadok.split(":");
				System.out.println(riadok);
				if(id_typ.getTyp()=='P' && id_typ.getId()==Integer.parseInt(arr[7])) {
					if(arr[5].equals(priezvisko)) nasiel = true;
				}
				else { //ak je to zubar tak v tom subore je jeho meno na 6 pozicii
					if(arr[6].equals(priezvisko)) nasiel = true;	
				}
				if(nasiel) {
					terminy.add(new Termin(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),
							Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),arr[5]));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Nepodarilo sa otvorit subor");
			e.printStackTrace();
			return null;
		}
		scan.close();
		return terminy;
	}
	public static ArrayList<Termin> nacitajVolneTerm(String zub) { //mozno neskor zmenit ze aby to zobrazilo iba podla zubara
		File f = new File(volne_term_cesta);
		ArrayList<Termin> zoznam = new ArrayList<Termin>();
		try {
			Scanner scan = new Scanner(f);
			while(scan.hasNextLine()) {
				String [] arr = scan.nextLine().split(":");
				if(zub.equals(arr[5])) {
					zoznam.add(new Termin(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),
							Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),arr[5]));
				}
			}
			scan.close();
			return zoznam;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	public static String najdiPrihlUd(int id) {
		File subor = new File(prihlasovania_cesta);
		try (Scanner scan = new Scanner(subor)) {
			while(scan.hasNextLine()) {
				String riadok = scan.nextLine();
				System.out.println(riadok);
				String []parts = riadok.split(":");
				if(Integer.parseInt(parts[0])==id) {
					return riadok;
				}
					
			}
			return null; //ak neexistuje 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
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
		int i=0;
		try (Scanner scan = new Scanner(subor)) {
			System.out.println("Otvoril som");
			System.out.println("ID "+id);
			while(scan.hasNextLine()) {
				i++;
				
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
				
				if("Z".equals(casti[1])) zubari.add(new Zubar(krstne_meno,priezvisko,ulica,cislo_domu,obec,vek,cislo,email,Integer.parseInt(casti[0]),'Z'));
				if("S".equals(casti[1])) sestricky.add(new Sestricka(krstne_meno,priezvisko,ulica,cislo_domu,obec,vek,cislo,email,Integer.parseInt(casti[0]),'S'));
				if("R".equals(casti[1])) recepcne.add(new Recepcna(krstne_meno,priezvisko,ulica,cislo_domu,obec,vek,cislo,email,Integer.parseInt(casti[0]),'R'));
				if("P".equals(casti[1])) {
					String rodne_c = casti[10];
					String zub = casti[11];
					String poistovna = casti[12];
					pacienti.add(new Pacient(krstne_meno,priezvisko,ulica,cislo_domu,obec,vek,cislo,email,Integer.parseInt(casti[0]),'P',rodne_c,poistovna,zub));
					
				}
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
			m.setPocetPo(i);
			return m;
		}
		return null; //ak nenajdem majitela
	}
	public static Pacient nacitajPacienta(String nick, int id,char typ,String heslo) {
		ArrayList<Termin> terminy = new ArrayList<Termin>(); //todo
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
					Pacient p = new Pacient(krstne_meno, priezvisko,ulica,cislo_domu,obec, vek, cislo,email,id,typ,casti[10],casti[11],casti[12]);
					p.pridajPrihl_udaje(nick, heslo);
					return p;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Nenasiel sa subor");
			e.printStackTrace();
		}
		return null;
	}
	public static Zamestnanec nacitajZamestnanca(String nick, int id,char typ,String heslo) {
		ArrayList<Pacient> pacienti  = new ArrayList<Pacient>();
		Zamestnanec zam = null;
		int i=-0;
		File subor = new File(uzivatelia_cesta);
		try (Scanner scan = new Scanner(subor)) {
			while(scan.hasNextLine()) {
				i++; //mam noveho pouzivatela
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

				if("P".equals(casti[1])) {
					String rodne_c = casti[10];
					String zub = casti[11];
					String poistovna = casti[12];
					pacienti.add(new Pacient(krstne_meno,priezvisko,ulica,cislo_domu,obec,vek,cislo,email,Integer.parseInt(casti[0]),'P',rodne_c,zub,poistovna));
					
				}
				//System.out.println("ID ="+casti[0]);
				//System.out.println("Hladam"+id);
				//nasiel som daneho zamestnanca nacitam udaje o nom 
				if(id==Integer.parseInt(casti[0])) {
					switch(typ){
					case 'Z':
						zam = new Zubar(krstne_meno, priezvisko,ulica,cislo_domu,obec, vek, cislo,email,id,typ);
						break;
					case 'S':
						zam = new Sestricka(krstne_meno, priezvisko,ulica,cislo_domu,obec, vek, cislo,email,id,typ);
						break;
					case 'R':
						zam = new Recepcna(krstne_meno, priezvisko,ulica,cislo_domu,obec, vek, cislo,email,id,typ);
					}
					zam.pridajPrihl_udaje(nick, heslo);
					zam.setPocetPo(i);
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
