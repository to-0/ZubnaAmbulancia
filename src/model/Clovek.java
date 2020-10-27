package model;

public class Clovek {
	public String meno;
	public String priezvisko;
	public int vek;
	public String tel_cislo;
	public String email;
	public Clovek(String firstn,String secondN, int age, String phone_num,String email) {
		this.meno=firstn;
		this.priezvisko = secondN;
		this.vek = age;
		this.tel_cislo = phone_num;
		this.email = email;
		
	}
}
