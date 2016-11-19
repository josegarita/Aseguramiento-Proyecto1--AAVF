package ws;
import java.util.Set;
import javax.ws.rs.core.Application;

// TODO: Auto-generated Javadoc
/**
 * The Class ApConfig.
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApConfig extends Application {
	
	/* (non-Javadoc)
	 * @see javax.ws.rs.core.Application#getClasses()
	 */
	@Override
	public final Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}
	
	/**
	 * Adds the rest resource classes.
	 *
	 * @param resources the resources
	 */
	private void addRestResourceClasses(Set<Class<?>> resources){
		resources.add(ws.ProductRestful.class);
	}
}
