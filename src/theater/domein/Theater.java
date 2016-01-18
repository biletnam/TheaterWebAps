package theater.domein;

import java.util.ArrayList;

import theater.data.KlantDAO;
import theater.data.TheaterException;
import theater.data.VoorstellingDAO;

/**
 * Representeert een theater en dient als frontcontroller voor de hele
 * domeinlaag.
 * 
 * @author Open Universiteit
 */
public class Theater {
  public static final int AANTALPERRIJ = 10;
  public static final int AANTALRIJEN = 15;
  private String naam = null;
  private ArrayList<Voorstelling> voorstellingen = null;

  /**
   * Constructor Creeert een theater en initialiseert de lijst met
   * voorstellingen
   * 
   * @param naam
   *          theaternaam
   */
  public Theater(String naam) throws TheaterException {
    this.naam = naam;
    this.voorstellingen = VoorstellingDAO.getVoorstellingen();
  }

  /**
   * Geeft de naam van het theater.
   * 
   * @return naam van het theater
   */
  public String getNaam() {
    return naam;
  }

  /**
   * Geeft de lijst met voorstellingen in de toekomst.
   * 
   * @return lijst met voorstellingen.
   */
  public ArrayList<Voorstelling> getVoorstellingen() {
    return voorstellingen;
  }

  /**
   * Levert een klant-object met gegeven naam en telefoonnummer
   * 
   * @param naam
   *          : de naam van de klant
   * @param telefoon
   *          :het telefoonnummer van de klant
   * @return een klant met gegeven naam en nummer, die ook in de database zit;<br>
   *         dwz als de gegevens niet gevonden worden, wordt een nieuwe klant in
   *         de database gezet
   * @throws TheaterException
   *           als er in Klantbeheer iets mis gaat
   */
  public Klant getKlant(String naam, String telefoon) throws TheaterException {
    return KlantDAO.getKlant(naam, telefoon);
  }

  /**
   * 
   * @param id
   *          id van voorstelling
   * @param aantal
   *          aantal plaatsen
   * @param naam
   *          naam klant
   * @param telefoon
   *          telefoon klant
   * @return Lijst met bestelde plaatsen of null
   * @throws TheaterException
   */
  public ArrayList<Plaats> reserveer(int id, int aantal, String naam,
      String telefoon) throws TheaterException {
    Klant klant = KlantDAO.getKlant(naam, telefoon);
    ArrayList<Plaats> plaats = null;
    boolean isUitverkocht = VoorstellingDAO.isUitverkocht(id);
    if (!isUitverkocht) {
      plaats = VoorstellingDAO.reserveer(id, klant.getId(), aantal, true);
    }
    return plaats;
  }
}
