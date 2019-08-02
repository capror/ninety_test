package testpackage.datatypes;

import java.time.LocalDate;
import java.lang.StringBuffer;

public class PositionData {
	// TODO: add getter and setters if the class becomes more complicated
	
	public long _id = 0;

	public long _eventTime;

	public double _longitutde;

	public double _latitude;

	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer("PositionData ");
		
		ret.append("[id: ").append(_id).append("], ");
		ret.append("[eventTime: ").append(_eventTime).append("], ");
		ret.append("[lang/lang: ").append(_longitutde).append("/").append(_latitude).append("]");

		return ret.toString();
	}
}