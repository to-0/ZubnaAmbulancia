package terminy;

public class RezerTermin extends Termin{
	private int id_pac;
	private String meno_pac;
	public RezerTermin(int den, int mesiac,int rok, int hodina, int minuta,String zubar,String pacient,int id) {
		super(den,mesiac,rok,hodina,minuta,zubar);
		this.id_pac = id;
		this.meno_pac = pacient;
	}
	@Override
	public String toWriter() {
		String s =  this.den+":"+this.mesiac+":"+this.rok+":"+this.hodina+":"+this.minuta+":"+this.zubar+":"+this.meno_pac+":"+this.id_pac;
		return s;
	}

}
