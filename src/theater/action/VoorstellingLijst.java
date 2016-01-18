/**
 * 
 */
package theater.action;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import theater.data.TheaterException;
import theater.data.VoorstellingDAO;
import theater.domein.Voorstelling;

/**
 * @author rogier
 *
 */
public class VoorstellingLijst extends VoorstellingBaseActionBean {
	private static final String VOORSTVIEW = "/WEB-INF/jsp/theater_voorstellingen.jsp";
	private static final String INLEZENVOORST = "Er is iets mis gegaan met inlezen van de voorstellingen.";

	
	@DefaultHandler
	public Resolution theaterlijst(){
		return new ForwardResolution(VOORSTVIEW);
	}
	public List<Voorstelling> getVoorstellingen(){
		try {
			return VoorstellingDAO.getVoorstellingen();
		} catch (TheaterException e) {
	    	getContext().getMessages().add(
		    		new SimpleMessage(INLEZENVOORST)
		    	);
		}
		return null;
	}
}
