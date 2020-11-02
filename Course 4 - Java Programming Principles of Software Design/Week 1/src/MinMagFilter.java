public class MinMagFilter implements Filter
{
    private double magMin; 
    
    public MinMagFilter(double min) { 
        magMin = min;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    } 
    
	@Override
	public String getName() {
		return "MinMag Filter";
	}
}
