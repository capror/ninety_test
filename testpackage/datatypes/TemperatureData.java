package testpackage.datatypes;

public class TemperatureData {
	// TODO: add getter and setters if the class becomes more complicated

	public long _id = 0;

	public long _eventTime;

	public double _temperature;

	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer("TemperatureData ");
		
		ret.append("[id: ").append(_id).append("], ");
		ret.append("[eventTime: ").append(_eventTime).append("], ");
		ret.append("[t: ").append(_temperature).append("]");

		return ret.toString();
	}	
}