package databaza;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import pouzivatelia.Pouzivatel;

public class Writer {
	public static final String prihlasovania_cesta = System.getProperty("user.dir")+"\\src\\databaza\\prihlasovania.txt";
	public static final String uzivatelia_cesta =  System.getProperty("user.dir")+"\\src\\databaza\\uzivatelia.txt";
	public static final String rez_term_cesta = System.getProperty("user.dir")+"\\src\\databaza\\rezervovane_terminy.txt";
	public static final String temp_cesta = System.getProperty("user.dir")+"\\src\\databaza\\temp.txt";
	public static final String vol_term_cesta = System.getProperty("user.dir")+"\\src\\databaza\\volne_terminy.txt";
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
		     String riadokVymaz = p.getId_typ().getId()+":"+p.getPrihlasUdaje().toWriter()+":"+p.getId_typ().getTyp();
		        while((riadok = reader.readLine()) != null) {
		            String trimmedLine = riadok.trim();
		            if(trimmedLine.equals(riadokVymaz)) {
		            	writer.write(p.getId_typ().getId()+":"+p.getPrihlasUdaje().getMeno()+":"+noveh+":"+p.getId_typ().getTyp()+System.getProperty("line.separator"));
		            	continue;
		            	}
		            writer.write(riadok + System.getProperty("line.separator"));
		            }        
		        writer.close(); 
		        reader.close(); 
		        inputFile.delete();
		        tempFile.renameTo(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Nieco sa pokazilo");
			e.printStackTrace();
			return;
		} catch (IOException e) {
			System.out.println("Nieco sa pokazilo");
			e.printStackTrace();
			return;
		}
	}
	public static boolean pridajRiadok(String cesta,String riadok) {
		try {
			FileWriter fileWritter = new FileWriter(cesta,true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
			bw.write("\n"+riadok); 
			bw.close();
			return true;
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
