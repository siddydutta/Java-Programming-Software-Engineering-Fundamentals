public class DistanceFilter implements Filter {
	private Location location;
	private double maxDistance;
	
	public DistanceFilter(Location location, double maxDistance) {
		this.location = location;
		this.maxDistance = maxDistance;
	}

	@Override
	public boolean satisfies(QuakeEntry qe) {
		if (location.distanceTo(qe.getLocation()) < maxDistance)
			return true;
		else
			return false;
	}
	
	@Override
	public String getName() {
		return "Distance Filter";
	}
}