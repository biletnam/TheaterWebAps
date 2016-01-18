/**
 * 
 */
package theater.action;

import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.SimpleMessage;
import theater.data.TheaterException;
import theater.data.VoorstellingDAO;
import theater.domein.Voorstelling;

/**
 * @author rogier
 *
 */
public class VoorstellingBaseActionBean extends BaseActionBean{
	private static final String INLEZENVOORST = "Er is iets mis gegaan met inlezen van de voorstellingen.";
	protected Integer voorstellingId;
	private Voorstelling voorstelling;

    public Integer getVoorstellingId() {
        return voorstellingId;
    }
    public void setVoorstellingId(Integer id) {
        voorstellingId = new Integer(id);
    }
    public Voorstelling getVoorstelling(){
    	if(voorstellingId != null){
    		try {
				for(Voorstelling v : VoorstellingDAO.getVoorstellingen()){
					if(v.getId() == voorstellingId.intValue()){
						setVoorstelling(v);
						return voorstelling;
					}
				}
			} catch (TheaterException e) {
		    	getContext().getMessages().add(
			    		new SimpleMessage(INLEZENVOORST)
			    	);
			}
    	}
    	return voorstelling;
    }
    public void setVoorstelling(Voorstelling voorstelling){
    	this.voorstelling = voorstelling;
    }
}
