import org.scalatest.funsuite.AnyFunSuite

class AppTest extends AnyFunSuite {

  test("Test formatter function"){
    val expectedValue = "IAmGoingHome"
    val input = "I am  going_home"
    val output = App.formatter(input)
    assert(output === expectedValue)

  }

  test("Test formatHeader function"){
    val input = "STATION,STATION_NAME,ELEVATION,LATITUDE,LONGITUDE,DATE,HPCP,Measurement Flag,Quality Flag"
    val output = App.formatHeader(input)
    val expectedValue = Array("Station","StationName","Elevation","Latitude","Longitude","Date","Hpcp","MeasurementFlag","QualityFlag")
    assert(output === expectedValue)
  }

}
