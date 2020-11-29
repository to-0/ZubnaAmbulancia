package model;

public class PrihlasovacieUdaje {
	private String meno;
	private String heslo;
	public PrihlasovacieUdaje() {
		// TODO Auto-generated constructor stub
	}
	public PrihlasovacieUdaje(String meno, String heslo) {
		this.meno = meno;
		this.heslo = heslo;
	}
	public String getHeslo() {
		return heslo;
	}
	public void setHeslo(String heslo) {
		this.heslo = heslo;
	}
	public String getMeno() {
		return meno;
	}
	public void setMeno(String meno) {
		this.meno = meno;
	}
	public String toWriter() {
		String s = this.meno+":"+this.heslo;
		return s;
	}

}
