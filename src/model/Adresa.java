package model;

public class Adresa {
	private String ulica;
	private int cislo_domu ;
	private String obec;
	public Adresa(String ulica, int cislo, String obec) {
		this.setUlica(ulica);
		this.setCislo_domu(cislo);
		this.setObec(obec);
	}
	public String toString() {
		String s = this.ulica+" "+this.cislo_domu+" "+obec;
		return s;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public int getCislo_domu() {
		return cislo_domu;
	}
	public void setCislo_domu(int cislo_domu) {
		this.cislo_domu = cislo_domu;
	}
	public String getObec() {
		return obec;
	}
	public void setObec(String obec) {
		this.obec = obec;
	}
}
