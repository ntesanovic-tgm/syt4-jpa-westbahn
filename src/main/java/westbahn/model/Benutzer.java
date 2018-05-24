package westbahn.model;

import javax.persistence.*;

@Entity
public class Benutzer {

	@Id
	@Column(name="id")
	private Long ID;

	@Column
	private String vorName;

	@Column
	private String nachName;

	@Column
	private String eMail;

	@Column
	private String passwort;

	@Column
	private String smsNummer;

	@Column
	private Long verbuchtePraemienMeilen;

	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "ticket")
	private Ticket tickets;

	@OneToMany
//(cascade = CascadeType.ALL, mappedBy = "comment_system", orphanRemoval = true)//(cascade=CascadeType.ALL)
	@JoinTable(
			name = "benutzer_reservierungen",
			joinColumns = {@JoinColumn(referencedColumnName = "id")},
			inverseJoinColumns = { @JoinColumn(referencedColumnName = "id") })
	private Reservierung[] reservierungen;

}
