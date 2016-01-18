package theater.domein;

import java.util.GregorianCalendar;

/**
 * Representeert een voorstelling.
 * 
 * @author Open Universiteit
 */
public class Voorstelling {

  private int id;
  private String naam = "";
  private GregorianCalendar datum = null;

  public String getNaam() {
    return naam;
  }

  public GregorianCalendar getDatum() {
    return datum;
  }

  public int getId() {
    return id;
  }

  /**
   * Creeert een voorstelling.
   * 
   * @param naam
   *          naam van de voorstelling
   * @param datum
   *          datum van de voorstelling
   */
  public Voorstelling(int id, String naam, GregorianCalendar datum) {
    this.id = id;
    this.naam = naam;
    this.datum = datum;
  }

  
}
