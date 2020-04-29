import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import  static io.restassured.RestAssured.*;
import  static org.hamcrest.Matchers.*;

import org.testng.Assert;

import POJO.place;
import files.ReuseableMethods;
import files.payload;

public class dummydeserialization {
	public static void main(String[] args) {
		
	
	String newaddress="South Africa";
    RestAssured.baseURI="https://rahulshettyacademy.com";
   String response= given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
    .body(payload.AddPlace()).when().post("maps/api/place/add/json")
      .then().assertThat().statusCode(200).body("scope",equalTo("APP"))//validating the body agrgument ex:scope
      .header("server", "Apache/2.4.18 (Ubuntu)") 
      .extract().response().asString();//validating the hedaer with server
   
   System.out.println(response);
   
   JsonPath js=ReuseableMethods.rawtoJson(response);
   String place_id=js.getString("place_id");
   
   System.out.println(place_id);
   
  //Updating the Place
   given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
   .body("{\r\n" + 
   		"\"place_id\":\""+place_id+"\",\r\n" + 
   		"\"address\":\""+newaddress+"\",\r\n" + 
   		"\"key\":\"qaclick123\"\r\n" + 
   		"}\r\n" + 
   		"")
   .when().put("maps/api/place/update/json")
   .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
   
   //Get request
  place getresponse= given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id).header("Content-Type","application/json")
  .expect().defaultParser(Parser.JSON)
   .when().get("maps/api/place/get/json").as(place.class);

   
   
  System.out.println("Accuracy:"+getresponse.getAccuracy());
  
  System.out.println("Latitude:"+getresponse.getLocation().getlat());
	
	}

}
