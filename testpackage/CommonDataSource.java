package testpackage;

import testpackage.datatypes.*;
import java.util.*;


public class CommonDataSource {
	
	private List<PositionData> _positionStream;
	private List<TemperatureData> _temperatureStream;

	private Iterator<PositionData> _itPosition;
	private Iterator<TemperatureData> _itTemperature;

	private long _maxMatchDelay;

	public CommonDataSource( List<PositionData> positionStream, List<TemperatureData> temperatureStream, long maxMatchDelay ) {
		_positionStream = positionStream;
		_temperatureStream = temperatureStream;
		_maxMatchDelay = maxMatchDelay;

		_itPosition = _positionStream.iterator();
		_itTemperature = _temperatureStream.iterator();
	}

	public MeasurementPoint getNext() {

		PositionData positionData;
		TemperatureData temperatureData;

		//System.out.println(" ----------------------------- ");
		
		// Assuming that temperature measurement must happen AFTER the position measurement
		// Actually any rule might be allpied. Here it's done only to simplify test task

		try {
		
			temperatureData = _itTemperature.next();

			do {
		
				positionData = _itPosition.next();

				if( positionData._eventTime > temperatureData._eventTime ) {

					while( _itTemperature.hasNext() && ( positionData._eventTime > temperatureData._eventTime ) ) {
						temperatureData = _itTemperature.next();
					}
				}

				if( ( positionData._eventTime <= temperatureData._eventTime ) && 
					( positionData._eventTime - temperatureData._eventTime <= _maxMatchDelay ) )
				{
					return new MeasurementPoint(positionData, temperatureData );
				}
				
			} while ( _itPosition.hasNext() );
			
		} catch( NoSuchElementException ex ) {}

		return null;
	}
}