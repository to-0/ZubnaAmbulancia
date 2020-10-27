package model;

public class Termin {
	int rok;
	int hodina;
	int minuta;
	int den;
	int mesiac;
	public String toString() {
		String s = den+"."+mesiac+"."+rok+" "+hodina+":"+minuta;
		return s;
	}
}
