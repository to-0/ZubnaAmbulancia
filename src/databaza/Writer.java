package databaza;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import jadro.App;
import pouzivatelia.Pacient;
import pouzivatelia.Pouzivatel;

public class Writer {
	static String prihlasovania_cesta = System.getProperty("user.dir")+"\\src\\databaza\\prihlasovania.txt";
	static String uzivatelia_cesta =  System.getProperty("user.dir")+"\\src\\databaza\\uzivatelia.txt";
	static String rez_term_cesta = System.getProperty("user.dir")+"\\src\\databaza\\rezervovane_terminy.txt";

	public Writer() {
		
	}
	public static void main(String[] args) {
		Pacient p = new Pacient("Tomko","Kalny","Stepnice",10,"Slovensky Grob",20,"0911498211","tomas1.kalny@gmail.com",
				3,'P');
		p.pridajPrihl_udaje("tomko", "tomino");
		Writer.zapisPouzivatela(p);
		System.out.println("Idem si");
	}
	static void pridajPrihlasenie(String meno, String heslo,int id, char typ) {
         try {
        	//File f = new File(prihlasovania_cesta);
    		//FileWriter fileWritter = new FileWriter(f.getName(),true);
        	 FileWriter fileWritter = new FileWriter(prihlasovania_cesta,true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            String data = id+":"+meno+":"+heslo+":"+typ;
			bw.write(data);
			bw.close();
			 System.out.println("Done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void zapisPouzivatela(Pouzivatel p) {
		System.out.println("I am outside ;)");
		try {
			System.out.println("I am inside ;)");
			//File f = new File(uzivatelia_cesta);
			
			FileWriter fileWritter = new FileWriter(uzivatelia_cesta,true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            
            PrintWriter out = new PrintWriter(bw);
            Writer.pridajPrihlasenie(p.getPrihlasUdaje().getMeno(),p.getPrihlasUdaje().getHeslo(),p.getId_typ().id,p.getId_typ().typ);
			bw.write("\n"+p.toWriter()); //nie som si isty ci to pojde aj ked pouzivatel bude pacient kde je to prekonane ta metoda
			//out.println(p.toWriter()); //toto  mi tu pekne vypise novy riadok idk
			bw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
