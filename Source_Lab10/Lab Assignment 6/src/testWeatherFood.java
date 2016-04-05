import org.junit.Test;

public class testWeatherFood {
	WeatherEstimater ws=new WeatherEstimater();
FindFood ff=new FindFood();
@Test
public void test()throws Exception
{
	String jsondata=ws.getWeather().toString();
	if(jsondata!=null)
	{
		System.out.println(" Weather Test Passed");
	}
	
}
@Test
public void test1()throws Exception
{
	String jsondata=ff.findFood().toString();
	if(jsondata!=null)
	{
		System.out.println("Food Test Passed");
	}
	
}

}
