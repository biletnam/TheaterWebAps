/**
 * 
 */
package theater.action;

import java.util.ArrayList;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.validation.Validate;
import theater.data.KlantDAO;
import theater.data.TheaterException;
import theater.data.VoorstellingDAO;
import theater.domein.Klant;
import theater.domein.Plaats;
import theater.domein.Theater;

/**
 * @author rogier
 *
 */
public class VoorstellingReserveerActionBean extends VoorstellingBaseActionBean{
	private static final String reserveerView = "/WEB-INF/jsp/theater_reserveer.jsp";
	private static final String BESTELDVIEW = "/WEB-INF/jsp/theater_besteld.jsp";
	private static final String KLANTMSG = "Er is iets mis gegaan met toevoegen/opvragen van de Klant.";
	private static final String UITVERKOCHT = "Er is ets mis gegaan met isUitverkocht.";
	private static final String RESERVEERDB = "Er is iets mis gegaan tijdesn het reseveren.";
	private static final String GETPLAATSDB = "Er is iets mis gegaan met ophalen van de plaatsen";
	private static final String GEENPLAATSEN = "Er zijn geen plaatsen meer beschikbaar";
	@Validate(required=true, on="bestel" ,minvalue=2
			, maxvalue=Theater.AANTALPERRIJ)
	private Integer aantal;
	@Validate(required=true, on="bestel", minlength=2, maxlength=40, trim=true)
	private String naam;
	@Validate(required=true, on="bestel", minlength=6, maxlength=11)
	private String telefoon;
    ArrayList<Plaats> plaats = null;
    Theater theater;
    Klant klant;
	
	public Integer getAantal(){
		return aantal;
	}
	public void setAantal(Integer aantal){
		this.aantal = aantal;
	}
	
	@DefaultHandler
	public Resolution reserveer(){
		return new ForwardResolution(reserveerView );
	}
	
	public Resolution bestel(){
		try {
			theater = getContext().getCurrentTheater();
			klant = theater.getKlant(naam, telefoon);
			//klant = KlantDAO.getKlant(naam, telefoon);
		} catch (TheaterException e) {
	    	getContext().getMessages().add(
		    		new SimpleMessage(KLANTMSG)
		    	);
		}
	    boolean isUitverkocht = false;
		try {
			isUitverkocht = VoorstellingDAO.isUitverkocht(getVoorstellingId().intValue());
		} catch (TheaterException e) {
	    	getContext().getMessages().add(
		    		new SimpleMessage(UITVERKOCHT)
		    	);
		}
	    if (!isUitverkocht) {
	      try {
			plaats = VoorstellingDAO.reserveer(getVoorstellingId().intValue(), klant.getId(), aantal, false);
		} catch (TheaterException e) {
	    	getContext().getMessages().add(
		    		new SimpleMessage(GETPLAATSDB)
		    	);
		    }
	    }
	    if (plaats != null){
	    	try {
				plaats = VoorstellingDAO.reserveer(getVoorstellingId().intValue(), klant.getId(), aantal, true);
				return new ForwardResolution(BESTELDVIEW);

			} catch (TheaterException e) {
		    	getContext().getMessages().add(
			    		new SimpleMessage(RESERVEERDB)
			    	);
			    }
	    }else{
	    	getContext().getMessages().add(
		    		new SimpleMessage(GEENPLAATSEN)
		    	);
	    }
    	return new ForwardResolution(reserveerView );
	}
	@DontValidate
    public Resolution cancel() {
        getContext().getMessages().add(
            new SimpleMessage("Action cancelled.")
        );
        return new RedirectResolution(VoorstellingLijst.class);
    }
	/**
	 * @return the telefoon
	 */
	public String getTelefoon() {
		return telefoon;
	}
	/**
	 * @param telefoon the telefoon to set
	 */
	public void setTelefoon(String telefoon) {
		this.telefoon = telefoon;
	}
	/**
	 * @return the naam
	 */
	public String getNaam() {
		return naam;
	}
	/**
	 * @param naam the naam to set
	 */
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public ArrayList<Plaats> getplaats(){
		return plaats;		
	}
	/**
	 * @return the klant
	 */
	public Klant getKlant() {
		return klant;
	}
}
