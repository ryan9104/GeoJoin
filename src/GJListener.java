import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import com.maxmind.geoip.LookupService;

public class GJListener extends PluginListener {
	private final HashMap<String, String> tags = new HashMap<String, String>();

	public void onLogin(Player player) {
		String parsedMsg = parseMsg(player);
		for (Player p : etc.getServer().getPlayerList()) {
			p.sendMessage(parsedMsg);
		}
	}

	public String datPath() {
		try {
			String path = GJListener.class.getProtectionDomain()
					.getCodeSource().getLocation().toURI().getPath();

			if (path.endsWith(".jar") || path.endsWith("/")) {
				path = path.substring(0, path.lastIndexOf("/"));
			}
			path = path.substring(0, path.lastIndexOf("/"));

			return path + File.separator + "GeoLiteCity.dat";
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String parseMsg(Player player) {
		String ip = player.getIP();
		String joinString = GeoJoin.properties.joinString;
		try {
			LookupService cl = new LookupService(datPath(),
					LookupService.GEOIP_MEMORY_CACHE);
			com.maxmind.geoip.Location l = cl.getLocation(ip);
			com.maxmind.geoip.Location s = cl.getLocation("74.125.95.103");

			// player shit
			tags.put("<player>", player.getName());
			tags.put("<ip>", ip);
			tags.put("<prefix>", player.getColor());

			// location tags
			tags.put("<city>", l.city);
			tags.put("<countrycode>", l.countryCode);
			tags.put("<countryname>", l.countryName);
			tags.put("<postalcode>", l.postalCode);
			tags.put("<region>", l.region);
			tags.put("<areacode>", Integer.toString(l.area_code));
			tags.put("<dmacode>", Integer.toString(l.dma_code));
			tags.put("<latitude>", Float.toString(l.latitude));
			tags.put("<longitude>", Float.toString(l.longitude));
			tags.put("<metrocode>", Integer.toString(l.metro_code));
			tags.put("<distance>", Double.toString(l.distance(s)));

			// color
			tags.put("<black>", Colors.Black);
			tags.put("<blue>", Colors.Blue);
			tags.put("<darkpurple>", Colors.DarkPurple);
			tags.put("<gold>", Colors.Gold);
			tags.put("<gray>", Colors.Gray);
			tags.put("<green>", Colors.Green);
			tags.put("<lightblue>", Colors.LightBlue);
			tags.put("<lightgray>", Colors.LightGray);
			tags.put("<lightgreen>", Colors.LightGreen);
			tags.put("<lightpurple>", Colors.LightPurple);
			tags.put("<navy>", Colors.Navy);
			tags.put("<purple>", Colors.Purple);
			tags.put("<red>", Colors.Red);
			tags.put("<rose>", Colors.Rose);
			tags.put("<white>", Colors.White);
			tags.put("<yellow>", Colors.Yellow);

			Iterator iterator = tags.keySet().iterator();
			while (iterator.hasNext()) {
				String tag = (String) iterator.next();
				String str = tags.get(tag);
				joinString = joinString.replaceAll(tag, str);
			}
			cl.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return joinString;
	}
}
