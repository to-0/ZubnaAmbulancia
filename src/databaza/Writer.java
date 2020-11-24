package databaza;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import jadro.App;
import model.Termin;
import pouzivatelia.Pacient;
import pouzivatelia.Pouzivatel;

public class Writer {
	public static String prihlasovania_cesta = System.getProperty("user.dir")+"\\src\\databaza\\prihlasovania.txt";
	public static String uzivatelia_cesta =  System.getProperty("user.dir")+"\\src\\databaza\\uzivatelia.txt";
	public static String rez_term_cesta = System.getProperty("user.dir")+"\\src\\databaza\\rezervovane_terminy.txt";
	public static String temp_cesta = System.getProperty("user.dir")+"\\src\\databaza\\temp.txt";
	public static String vol_term_cesta = System.getProperty("user.dir")+"\\src\\databaza\\volne_terminy.txt";


	public Writer() {
		
	}
	public static void main(String[] args) {
		Pacient p = new Pacient("Maly","Loptos","Loptova",10,"Loptovo",20,"0911111111","toto.email@email.email",
				3,'P');
		p.pridajPrihl_udaje("loptos", "123");
		Writer.zapisPouzivatela(p);
	}
	static void pridajPrihlasenie(String meno, String heslo,int id, char typ) {
         try {
        	//File f = new File(prihlasovania_cesta);
    		//FileWriter fileWritter = new FileWriter(f.getName(),true);
        	 FileWriter fileWritter = new FileWriter(prihlasovania_cesta,true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            String data = id+":"+meno+":"+heslo+":"+typ;
			bw.write("\n"+data);
			bw.close();
			 System.out.println("Done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void zapisPouzivatela(Pouzivatel p) {
		try {
			//File f = new File(uzivatelia_cesta);
			FileWriter fileWritter = new FileWriter(uzivatelia_cesta,true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            
            PrintWriter out = new PrintWriter(bw);
            Writer.pridajPrihlasenie(p.getPrihlasUdaje().getMeno(),p.getPrihlasUdaje().getHeslo(),p.getId_typ().getId(),p.getId_typ().getTyp());
			bw.write("\n"+p.toWriter()); //nie som si isty ci to pojde aj ked pouzivatel bude pacient kde je to prekonane ta metoda
			//out.println(p.toWriter()); //toto  mi tu pekne vypise novy riadok idk
			bw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static boolean vymazRiadok(String cesta, String riadok) {
		File inputFile = new File(cesta);
		File tempFile = new File(temp_cesta);
		try {
			 BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		     String riadok_c;
		        while((riadok_c = reader.readLine()) != null) {
		            String trimmedLine = riadok_c.trim();
		            if(trimmedLine.equals(riadok)) {
		            	continue;
		            	}
		            writer.write(riadok_c + System.getProperty("line.separator"));
		            }        
		        writer.close(); 
		        reader.close(); 
		        inputFile.delete();
		        boolean successful = tempFile.renameTo(inputFile);
		        if(successful) return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	public static void zmenHeslo(Pouzivatel p,String noveh) {
		File inputFile = new File(prihlasovania_cesta);
        File tempFile = new File(temp_cesta);
        try {
			 BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		     String riadok;
		     String riadokVymaz = p.toWriter();
		        while((riadok = reader.readLine()) != null) {
		            String trimmedLine = riadok.trim();
		            if(trimmedLine.equals(riadokVymaz)) {
		            	writer.write(p.getId_typ().getId()+":"+p.getPrihlasUdaje().getMeno()+":"+noveh+":"+p.getId_typ().getTyp());
		            	continue;
		            	}
		            writer.write(riadok + System.getProperty("line.separator"));
		            }        
		        writer.close(); 
		        reader.close(); 
		        inputFile.delete();
		        boolean successful = tempFile.renameTo(inputFile);
		        if(successful) Writer.vymazRiadok(prihlasovania_cesta,p.getId_typ().getId()+":"+p.getPrihlasUdaje().getMeno()+":"
		        		+p.getPrihlasUdaje().getHeslo()+":"+p.getId_typ().getTyp());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean pridajTerm(Termin t) {
		try {
			FileWriter fileWritter = new FileWriter(vol_term_cesta,true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            PrintWriter out = new PrintWriter(bw);
			bw.write("\n"+t.toWriter()); //nie som si isty ci to pojde aj ked pouzivatel bude pacient kde je to prekonane ta metoda
			bw.close();
			return true;
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
