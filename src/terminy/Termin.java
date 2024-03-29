package terminy;

import model.TermPouzInterface;

public abstract class Termin implements TermPouzInterface {
	int rok;
	int hodina;
	int minuta;
	int den;
	int mesiac;
	String zubar;
	public Termin( int den, int mesiac,int rok, int hodina, int minuta,String zubar) {
		this.rok = rok;
		this.hodina = hodina;
		this.minuta = minuta;
		this.den = den;
		this.mesiac = mesiac;
		this.zubar = zubar;
	}
	public String toString() {
		String s = this.den+"."+this.mesiac+"."+this.rok+" "+this.hodina+":"+this.minuta
				+"\n Osetrujuci zubar: "+this.zubar;
		return s;
	}
	
	
}
