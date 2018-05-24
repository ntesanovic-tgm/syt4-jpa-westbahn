package westbahn.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Zug {

	@Id
	private long ID;

	private Date startZeit;

	private int sitzPlaetze = 500;

	private int fahrradStellplaetze = 50;

	private int rollStuhlPlaetze = 10;

	private Bahnhof start;

	private Bahnhof ende;

}
