package westbahn.model;


import javax.persistence.*;

@NamedQuery(
        name = "Bahnhof.findById",
        query = "SELECT b FROM Bahnhof b WHERE b.ID = :bahnhof_id"
)

@Entity
public class Bahnhof {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;

	@Column
	private String name;

	@Column
	private int absPreisEntfernung;

	@Column
	private int absKmEntfernung;

	@Column
	private int absZeitEntfernung;

	@Column
	private boolean kopfBahnhof;

	public Bahnhof() {}

    public Bahnhof(String name, int absKmEntfernung, int absZeitEntfernung, boolean kopfBahnhof) {
        this.name = name;
        this.absPreisEntfernung = absPreisEntfernung;
        this.absKmEntfernung = absKmEntfernung;
        this.absZeitEntfernung = absZeitEntfernung;
        this.kopfBahnhof = kopfBahnhof;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAbsPreisEntfernung() {
        return absPreisEntfernung;
    }

    public void setAbsPreisEntfernung(int absPreisEntfernung) {
        this.absPreisEntfernung = absPreisEntfernung;
    }

    public int getAbsKmEntfernung() {
        return absKmEntfernung;
    }

    public void setAbsKmEntfernung(int absKmEntfernung) {
        this.absKmEntfernung = absKmEntfernung;
    }

    public int getAbsZeitEntfernung() {
        return absZeitEntfernung;
    }

    public void setAbsZeitEntfernung(int absZeitEntfernung) {
        this.absZeitEntfernung = absZeitEntfernung;
    }

    public boolean isKopfBahnhof() {
        return kopfBahnhof;
    }

    public void setKopfBahnhof(boolean kopfBahnhof) {
        this.kopfBahnhof = kopfBahnhof;
    }
}
