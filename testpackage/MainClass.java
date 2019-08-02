
package testpackage;

import static java.lang.System.out;
import java.lang.StringBuffer;
import java.lang.Thread;
import java.lang.Math;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import testpackage.datatypes.*;



public class MainClass {

	private int _streamSize;

	public MainClass setStreamSize( int streamSize ) {
		_streamSize = streamSize;
		return this;
	}

	
	private long _positionPeriod;
	public MainClass setPositionPeriod( long positionPeriod ) {
		_positionPeriod = positionPeriod;
		return this;
	}

	private long _temperaturePeriod;
	public MainClass setTemperaturePeriod( long temperaturePeriod ) {
		_temperaturePeriod = temperaturePeriod;
		return this;
	}

	private double _maxTemperatureTimeThreshould;
	public MainClass setMaxTemperatureTimeThreshould( long maxTemperatureTimeThreshould ) {
		_maxTemperatureTimeThreshould = maxTemperatureTimeThreshould;
		return this;
	}

	private double _temperatureRangeAbs;
	public MainClass setTemperatureRangeAbs( long temperatureRangeAbs ) {
		_temperatureRangeAbs = temperatureRangeAbs;
		return this;
	}

	private double _langLongRangeAbs;
	public MainClass setLangLongRangeAbs( long langLongRangeAbs ) {
		_langLongRangeAbs = langLongRangeAbs;
		return this;
	}


	static private List<PositionData> _positionStream;

	static private List<TemperatureData> _temperatureStream;



	static private double getDoubleInRange( double absRange ) {
		Random randomizer = new Random();
		return  absRange * ( -1 + 2 * randomizer.nextDouble() );
	}

	private void initStreams() {

		_positionStream = new ArrayList<PositionData>();

		_temperatureStream = new ArrayList<TemperatureData>();


		
		long temperatureId = 0;

		Random randomizer = new Random();

		for( long iPosition = 0; iPosition < _streamSize; ++iPosition ) {

			PositionData nextPosition = new PositionData();
			nextPosition._id = iPosition;
			nextPosition._eventTime = iPosition * _positionPeriod;
			nextPosition._longitutde = getDoubleInRange( _langLongRangeAbs );
			nextPosition._latitude = getDoubleInRange( _langLongRangeAbs );

			_positionStream.add( nextPosition );

			// let's have 1-5 temperature points for every position point
			int temperaturePointsNum = Math.abs( randomizer.nextInt() ) % 5 + 1;


			for( int iTempPoint = 1; iTempPoint <= temperaturePointsNum; ++iTempPoint ) {

				TemperatureData nextTemperature = new TemperatureData();
				nextTemperature._id = temperatureId++;
				
				long delta = (long)( getDoubleInRange( _maxTemperatureTimeThreshould * getDoubleInRange( 1.0 ) ) );

				nextTemperature._eventTime = nextPosition._eventTime 
											+ iTempPoint * (long)( _positionPeriod / ( temperaturePointsNum + 1 ) ) 
											+ delta;

				nextTemperature._temperature = getDoubleInRange( _temperatureRangeAbs );
				
				_temperatureStream.add( nextTemperature );
			}
		}
	}


	public static void main(String[] args) {

		// Let's leave it here anyway
		System.out.println("wow!");

		MainClass mc = new MainClass();

		mc.setStreamSize( 100 )
			.setPositionPeriod( 1000 )
			.setTemperaturePeriod( 300 )
			.setMaxTemperatureTimeThreshould( 100 )
			.setTemperatureRangeAbs( 100 )
			.setLangLongRangeAbs( 90 );

		long maxMatchDelay = 300;

		
		mc.initStreams();

		CommonDataSource dataSource = new CommonDataSource( _positionStream, _temperatureStream, maxMatchDelay );

		MeasurementPoint mp = dataSource.getNext();

		while( mp != null  ) {
			System.out.println( mp );
			mp = dataSource.getNext();
		}
	}

}
