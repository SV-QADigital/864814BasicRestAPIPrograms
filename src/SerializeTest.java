import io.restassured.RestAssured;
import io.restassured.response.Response;

import  static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import POJO.Location;

import POJO.place;

public class SerializeTest {

	public static void main(String[] args) {
		
		place p=new place();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setName("Frontline house");
		List<String> mylist=new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		p.setTypes(mylist);
        Location l=new Location();
		l.setlat(-38.383494);
		l.setlng(33.427362);
		p.setLocation(l);
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		 Response  res=given().queryParam("key", "qaclick123")
		.body(p)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		 
		String response= res.asString();
		 System.out.println(response);
		
	
	}
}
