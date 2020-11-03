package model;

public class Termin {
	public int rok;
	public int hodina;
	public int minuta;
	public int den;
	public int mesiac;
	public String toString() {
		String s = den+"."+mesiac+"."+rok+" "+hodina+":"+minuta;
		return s;
	}
}
