package westbahn.model;


import javax.persistence.Id;

public class Bahnhof {

	@Id
	private long ID;

	private String name;

	private int absPreisEntfernung;

	private int absKmEntfernung;

	private int absZeitEntfernung;

	private boolean kopfBahnhof;

}
