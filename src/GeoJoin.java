import java.util.logging.Logger;

public class GeoJoin extends Plugin {
	static final GJListener listener = new GJListener();
	public static PropertiesFile properties = new PropertiesFile("GeoJoin.properties");
    private Logger log;
    private String name = "GeoJoin";
    private String version = "0.7";

    public void enable() {
    }
    
    public void disable() {
    }

    public void initialize() {
        log = Logger.getLogger("Minecraft");
        log.info(name + " " + version + " initialized");
        
        etc.getLoader().addListener(PluginLoader.Hook.LOGIN,listener,this,PluginListener.Priority.MEDIUM);
    }
}