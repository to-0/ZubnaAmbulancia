package databaza;

public class ID_typ {
	private int id;
	private char rola; //zubar, sestricka, recepcia, majitel, pacient
	public ID_typ(int id, char povolanie) {
		this.id = id;
		this.rola = povolanie;
	}
	public void setId(int val) {
		this.id = val;
	}
	public int getId() {
		return this.id;
	}
	public void setRola(char c) {
		this.rola= c;
	}
	public char getRola() {
		return this.rola;
	}
}
