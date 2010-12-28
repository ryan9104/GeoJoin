public class Properties {

	public PropertiesFile properties = new PropertiesFile("GeoJoin.properties");
	public String joinString;

	public void Read() {
		try {
			this.properties.load();
			this.joinString = this.properties.getString("joinString");
			if (!this.properties.containsKey("joinString")){
				joinString = "<prefix><player> has joined the server from <countryname>";
			}
		} catch (Exception e) {
		}
	}
	
	public void Write() {
		try {
			this.properties.setString("joinString", this.joinString);
		} catch (Exception e){
		}
	}
	
}
