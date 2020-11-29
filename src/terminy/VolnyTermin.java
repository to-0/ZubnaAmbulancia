package terminy;

public class VolnyTermin extends Termin {

	public VolnyTermin(int den, int mesiac,int rok, int hodina, int minuta,String zubar) {
		super(den,mesiac,rok,hodina,minuta,zubar);
	}
	
	public String toWriter() {
		String s =  this.den+":"+this.mesiac+":"+this.rok+":"+this.hodina+":"+this.minuta+":"+this.zubar;
		return s;
	}
	public RezerTermin toRezTerm(int id, String meno) {
		RezerTermin t = new RezerTermin(this.den,this.mesiac,this.rok,this.hodina,
				this.minuta,this.zubar,meno,id);
		return t;
	}
}
