package testpackage.datatypes;

public class MeasurementPoint {
	// TODO: add getter and setters if the class becomes more complicated

	static private long _cnt = 0;

	public long _id = 0;

	public PositionData _position;

	public TemperatureData _temperature;

	public MeasurementPoint( PositionData position, TemperatureData temperature ) {
		_id = _cnt++;

		_position = position;
		_temperature = temperature;
	}

	@Override
	public String toString() {
		return "MeasurementPoint: " + _position + " " + _temperature; 
	}

}