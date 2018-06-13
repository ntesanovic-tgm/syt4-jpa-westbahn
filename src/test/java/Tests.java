import junit.framework.TestCase;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Test;
import westbahn.Kreditkarte;
import westbahn.Maestro;
import westbahn.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Tests extends TestCase {

    private static final Logger logger = LogManager.getLogger(Tests.class.getName());

    EntityManagerFactory factory;
    EntityManager em;

    static SimpleDateFormat dateForm = new SimpleDateFormat("dd.MM.yyyy");
    static SimpleDateFormat timeForm = new SimpleDateFormat("dd.MM.yyyy mm:hh");

    @Override
    public void setUp() throws Exception {
        super.setUp();
        factory = Persistence.createEntityManagerFactory("westbahn");
        em = factory.createEntityManager();

        deleteAll();
    }

    @Test
    public void testCreateBahnhof(){
        logger.info("Creating Bahnhof ... ");
        Bahnhof bahnhof = new Bahnhof(
                "Tulln Hauptbahnhof",
                11,
                100,
                true
        );

        saveObject(bahnhof);

        Bahnhof queriedBahnhof = (Bahnhof) em.createQuery("SELECT b FROM Bahnhof b WHERE b.name='Tulln Hauptbahnhof'").getSingleResult();

        assertEquals(bahnhof, queriedBahnhof);

        logger.info("Bahnhof successfully created!");

        deleteAll();
    }

    @Test
    public void testCreateBenutzer(){
        logger.info("Creating Benutzer ... ");
        Benutzer benutzer = new Benutzer(
                "David",
                "Hofer",
                "david.hofer@gmail.com",
                "hallo123",
                "+43680987654321"
        );

        saveObject(benutzer);

        Benutzer queriedBenutzer = (Benutzer) em.createQuery("SELECT b FROM Benutzer b WHERE b.nachName='Hofer'").getSingleResult();

        assertEquals(benutzer, queriedBenutzer);

        logger.info("Benutzer successfully created!");

        deleteAll();
    }

    @Test
    public void testCreateStrecke(){

        logger.info("Creating Strecke ... ");

        Bahnhof bahnhofTulln = new Bahnhof(
                "Tulln Hauptbahnhof",
                11,
                100,
                true
        );

        Bahnhof bahnhofSpittelau = new Bahnhof(
                "Wien Spittelau",
                11,
                100,
                true
        );

        Strecke streckeSpittelauTulln = new Strecke(
                bahnhofSpittelau,
                bahnhofTulln
        );

        saveObject(bahnhofTulln);
        saveObject(bahnhofSpittelau);
        saveObject(streckeSpittelauTulln);

        Strecke queriedStrecke = (Strecke) em.createQuery("SELECT s FROM Strecke s WHERE s.start=:start").setParameter("start", bahnhofSpittelau).getSingleResult();

        assertEquals(streckeSpittelauTulln, queriedStrecke);

        logger.info("Strecke successfully created!");

        deleteAll();
    }

    @Test
    public void testCreateZug() throws ParseException {

        logger.info("Creating Zug ... ");

        Bahnhof bahnhofTulln = new Bahnhof(
                "Tulln Hauptbahnhof",
                11,
                100,
                true
        );

        Bahnhof bahnhofSpittelau = new Bahnhof(
                "Wien Spittelau",
                11,
                100,
                true
        );

        Zug zug = new Zug(
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                500, 50, 10,
                bahnhofSpittelau,
                bahnhofTulln
        );

        saveObject(bahnhofTulln);
        saveObject(bahnhofSpittelau);
        saveObject(zug);

        Zug queriedZug = (Zug) em.createQuery("SELECT z FROM Zug z WHERE z.start=:start").setParameter("start", bahnhofSpittelau).getSingleResult();

        assertEquals(zug, queriedZug);

        logger.info("Zug successfully created!");

        deleteAll();
    }

    @Test
    public void testCreateReservierung() throws ParseException{

        logger.info("Creating Reservierung ... ");

        Kreditkarte kreditkarte = new Kreditkarte();

        Benutzer benutzer = new Benutzer(
                "David",
                "Hofer",
                "david.hofer@gmail.com",
                "hallo123",
                "+43680987654321"
        );

        Bahnhof bahnhofTulln = new Bahnhof(
                "Tulln Hauptbahnhof",
                11,
                100,
                true
        );

        Bahnhof bahnhofSpittelau = new Bahnhof(
                "Wien Spittelau",
                11,
                100,
                true
        );

        Strecke streckeSpittelauTulln = new Strecke(
                bahnhofSpittelau,
                bahnhofTulln
        );

        Zug zug = new Zug(
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                500, 50, 10,
                bahnhofSpittelau,
                bahnhofTulln
        );

        Reservierung reservierung = new Reservierung(
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                15,
                150,
                StatusInfo.ONTIME,
                streckeSpittelauTulln,
                zug,
                benutzer,
                kreditkarte
        );

        saveObject(benutzer);
        saveObject(bahnhofSpittelau);
        saveObject(bahnhofTulln);
        saveObject(streckeSpittelauTulln);
        saveObject(zug);
        saveObject(reservierung);

        Reservierung queriedReservierung = (Reservierung) em.createQuery("SELECT r FROM Reservierung r WHERE r.benutzer=:benutzer").setParameter("benutzer", benutzer).getSingleResult();

        assertEquals(reservierung, queriedReservierung);

        logger.info("Reservierung successfully created!");

        deleteAll();
    }

    @Test
    public void testCreateEinzelticketGrossgepaeck(){

        logger.info("Creating Einzelticket - Grossgepaeck ... ");

        Maestro maestro = new Maestro();

        Bahnhof bahnhofFranzJoseph = new Bahnhof(
                "Wien Franz-Joseph-Bahnhof",
                11,
                100,
                true
        );

        Bahnhof bahnhofSpittelau = new Bahnhof(
                "Wien Spittelau",
                11,
                100,
                true
        );

        Strecke streckeSpittelauFranzJoseph = new Strecke(
                bahnhofSpittelau,
                bahnhofFranzJoseph
        );

        Ticket einzelticketFahrrad = new Einzelticket(
                streckeSpittelauFranzJoseph,
                maestro,
                TicketOption.GROSSGEPAECK
        );

        saveObject(bahnhofSpittelau);
        saveObject(bahnhofFranzJoseph);
        saveObject(streckeSpittelauFranzJoseph);
        saveObject(einzelticketFahrrad);

        Einzelticket queriedEinzeltticket = (Einzelticket) em.createQuery("SELECT t FROM Ticket t WHERE t.strecke=:strecke").setParameter("strecke", streckeSpittelauFranzJoseph).getSingleResult();

        assertEquals("GROSSGEPAECK", queriedEinzeltticket.getTicketOption().toString());

        logger.info("Einzelticket with Grossgepaeck successfully created!");

        deleteAll();
    }

    @Test
    public void testCreateEinzelticketFahrrad(){

        logger.info("Creating Einzelticket - Fahrrad ... ");

        Maestro maestro = new Maestro();

        Bahnhof bahnhofFranzJoseph = new Bahnhof(
                "Wien Franz-Joseph-Bahnhof",
                11,
                100,
                true
        );

        Bahnhof bahnhofSpittelau = new Bahnhof(
                "Wien Spittelau",
                11,
                100,
                true
        );

        Strecke streckeSpittelauFranzJoseph = new Strecke(
                bahnhofSpittelau,
                bahnhofFranzJoseph
        );

        Ticket einzelticketFahrrad = new Einzelticket(
                streckeSpittelauFranzJoseph,
                maestro,
                TicketOption.FAHRRAD
        );

        saveObject(bahnhofSpittelau);
        saveObject(bahnhofFranzJoseph);
        saveObject(streckeSpittelauFranzJoseph);
        saveObject(einzelticketFahrrad);

        Einzelticket queriedEinzeltticket = (Einzelticket) em.createQuery("SELECT t FROM Ticket t WHERE t.strecke=:strecke").setParameter("strecke", streckeSpittelauFranzJoseph).getSingleResult();

        assertEquals("FAHRRAD", queriedEinzeltticket.getTicketOption().toString());

        logger.info("Einzelticket with Fahrrad successfully created!");

        deleteAll();
    }

    @Test
    public void testCreateZeitkarteWoche() throws ParseException{

        logger.info("Creating Wochenzeitkarte ... ");

        Kreditkarte kreditkarte = new Kreditkarte();

        Bahnhof bahnhofTulln = new Bahnhof(
                "Tulln Bahnhof",
                11,
                100,
                true
        );

        Bahnhof bahnhofMuckendorf = new Bahnhof(
                "Muckendorf-Wipfing Bahnhof",
                11,
                100,
                true
        );

        Strecke streckeTullnMuckendorf = new Strecke(
                bahnhofTulln,
                bahnhofMuckendorf
        );

        Ticket zeitkarteWoche = new Zeitkarte(
                streckeTullnMuckendorf,
                kreditkarte,
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                ZeitkartenTyp.WOCHENKARTE
        );

        saveObject(bahnhofTulln);
        saveObject(bahnhofMuckendorf);
        saveObject(streckeTullnMuckendorf);
        saveObject(zeitkarteWoche);

        Zeitkarte queriedZeitkarteWoche = (Zeitkarte) em.createQuery("SELECT z FROM Ticket z WHERE z.strecke=:strecke").setParameter("strecke", streckeTullnMuckendorf).getSingleResult();

        assertEquals(((Zeitkarte) zeitkarteWoche).getTyp(), queriedZeitkarteWoche.getTyp());

        logger.info("Wochenkarte successfully created!");

        deleteAll();
    }

    @Test
    public void testCreateZeitkarteMonat() throws ParseException{

        logger.info("Creating Monatskarte ... ");

        Maestro maestro = new Maestro();

        Bahnhof bahnhofTulln = new Bahnhof(
                "Tulln Bahnhof",
                11,
                100,
                true
        );

        Bahnhof bahnhofMuckendorf = new Bahnhof(
                "Muckendorf-Wipfing Bahnhof",
                11,
                100,
                true
        );

        Strecke streckeTullnMuckendorf = new Strecke(
                bahnhofTulln,
                bahnhofMuckendorf
        );

        Ticket zeitkarteMonat = new Zeitkarte(
                streckeTullnMuckendorf,
                maestro,
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                ZeitkartenTyp.MONATSKARTE
        );

        saveObject(bahnhofTulln);
        saveObject(bahnhofMuckendorf);
        saveObject(streckeTullnMuckendorf);
        saveObject(zeitkarteMonat);

        Zeitkarte queriedZeitkarteMonat = (Zeitkarte) em.createQuery("SELECT z FROM Ticket z WHERE z.strecke=:strecke").setParameter("strecke", streckeTullnMuckendorf).getSingleResult();

        assertEquals(((Zeitkarte) zeitkarteMonat).getTyp(), queriedZeitkarteMonat.getTyp());

        logger.info("Monatskarte successfully created!");

        deleteAll();
    }

    @Test
    public void testCreateZeitkarteJahr() throws ParseException{

        logger.info("Creating Jahreskarte ... ");

        Kreditkarte kreditkarte = new Kreditkarte();

        Bahnhof bahnhofTulln = new Bahnhof(
                "Tulln Bahnhof",
                11,
                100,
                true
        );

        Bahnhof bahnhofMuckendorf = new Bahnhof(
                "Muckendorf-Wipfing Bahnhof",
                11,
                100,
                true
        );

        Strecke streckeMuckendorfTulln = new Strecke(
                bahnhofMuckendorf,
                bahnhofTulln
        );

        Ticket zeitkarteJahr = new Zeitkarte(
                streckeMuckendorfTulln,
                kreditkarte,
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                ZeitkartenTyp.JAHRESKARTE
        );

        saveObject(bahnhofTulln);
        saveObject(bahnhofMuckendorf);
        saveObject(streckeMuckendorfTulln);
        saveObject(zeitkarteJahr);

        Zeitkarte queriedZeitkarteJahr = (Zeitkarte) em.createQuery("SELECT z FROM Ticket z WHERE z.strecke=:strecke").setParameter("strecke", streckeMuckendorfTulln).getSingleResult();

        assertEquals(((Zeitkarte) zeitkarteJahr).getTyp(), queriedZeitkarteJahr.getTyp());

        logger.info("Jahreskarte successfully created!");

        deleteAll();
    }

    @Test
    public void testCreateSonderangebot() throws ParseException{

        logger.info("Creating Sonderangebot ... ");

        Sonderangebot sonderangebot = new Sonderangebot(
                100,
                new java.sql.Date(dateForm.parse("10.03.2018").getTime()),
                200,
                0.5f
        );

        saveObject(sonderangebot);

        Sonderangebot queriedSonderangebot = (Sonderangebot) em.createQuery("SELECT s FROM Sonderangebot s WHERE s.preisNachlass=:preis").setParameter("preis", 0.5f).getSingleResult();

        assertEquals(sonderangebot, queriedSonderangebot);

        logger.info("Sonderangebot successfully created!");

        deleteAll();

    }

    @Test
    public void testRemoveBahnhof(){

        logger.info("Creating and Removing Bahnhof... ");

        em.getTransaction().begin();
        Bahnhof bahnhof = new Bahnhof(
                "Tulln Hauptbahnhof",
                11,
                100,
                true
        );
        em.persist(bahnhof);
        em.flush();
        em.getTransaction().commit();

        em.getTransaction().begin();
        em.remove(bahnhof);
        em.flush();
        em.getTransaction().commit();

        assertFalse(em.contains(bahnhof));

        logger.info("Successfully created and deleted a Bahnhof!");

        deleteAll();

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        deleteAll();

        em.close();
        factory.close();
    }

    public void deleteAll(){
        em.getTransaction().begin();

        em.createQuery("DELETE FROM Bahnhof").executeUpdate();
        em.createQuery("DELETE FROM Benutzer").executeUpdate();
        em.createQuery("DELETE FROM Reservierung ").executeUpdate();
        em.createQuery("DELETE FROM Sonderangebot ").executeUpdate();
        em.createQuery("DELETE FROM Strecke ").executeUpdate();
        em.createQuery("DELETE FROM Ticket").executeUpdate();
        em.createQuery("DELETE FROM Zug").executeUpdate();

        em.getTransaction().commit();
    }

    public void saveObject(Object o){
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
    }

}
