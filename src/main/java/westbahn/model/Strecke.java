package westbahn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

public class Strecke {

	@Id
	private long ID;

	private Bahnhof start;

	private Bahnhof bahnhof;

	private Bahnhof ende;

}
