package pouzivatelia;

public class Recepcna extends Zamestnanec {

	public Recepcna(String meno,String priezvisko,String ulica, int cislo_domu, String obec, int vek, String telefon,String email,int id, char typ) {
		super(meno, priezvisko,ulica,cislo_domu, obec, vek, telefon, email, id, typ);
		// TODO Auto-generated constructor stub
	}
	public Pacient pridajPacienta(String krstne,String priezvisko, String ulica, int cislo, String obec, int vek,
			String tel,String email,String nick, String heslo) {
		int id = this.pocetPo+1;
		this.pocetPo++;
		Pacient p = new Pacient(krstne,priezvisko,ulica,cislo,obec,vek,tel,email,id,'P');
		p.pridajPrihl_udaje(nick, heslo);
		return p;
	}

}
