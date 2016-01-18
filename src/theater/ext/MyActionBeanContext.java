/**
 * 
 */
package theater.ext;

import net.sourceforge.stripes.action.ActionBeanContext;

import theater.data.TheaterException;
import theater.domein.Theater;

/**
 * @author Rogier
 *
 */

public class MyActionBeanContext extends ActionBeanContext {
	private static final String THEATER = "theater";

	public Theater getCurrentTheater() {
		Theater theater = null;
		try {
			theater = new Theater("Rogier's Theater");
		} catch (TheaterException e) {
			System.out.println(e.getMessage());
		}
		return getCurrent(THEATER, theater);
	}
    public void setCurrentTheater(Theater theater) {
        setCurrent(THEATER, theater);
    }

	protected void setCurrent(String key, Object value) {
		getRequest().getSession().setAttribute(key, value);
	}

	@SuppressWarnings("unchecked")
	protected <T> T getCurrent(String key, T defaultValue) {
		T value = (T) getRequest().getSession().getAttribute(key);
		if (value == null) {
			value = defaultValue;
			setCurrent(key, value);
		}
		return value;
	}
}
