package model;

public class ID_typ {
	public int id;
	public char typ; //zubar, sestricka, recepcia, majitel, pacient
	public ID_typ(int id, char povolanie) {
		this.id = id;
		this.typ = povolanie;
	}
	public void setId(int val) {
		this.id = val;
	}
	public int getId() {
		return this.id;
	}
	public void setTyp(char c) {
		this.typ= c;
	}
	public char getTyp() {
		return this.typ;
	}
}
