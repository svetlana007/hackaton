
public class GeoCoordinate {
	public GeoCoordinate(double latitude,double lat_direction, double longitude, double long_direction) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	private double latitude;
	private double lat_direction;
	private double longitude;
	private double long_direction; 
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	

}
