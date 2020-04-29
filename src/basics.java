import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import  static io.restassured.RestAssured.*;
import  static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReuseableMethods;
import files.payload;

public class basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
    String getresponse= given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id).header("Content-Type","application/json")
     .when().get("maps/api/place/get/json")
     .then().log().all().assertThat().statusCode(200).extract().response().asString();
    
    JsonPath js2=ReuseableMethods.rawtoJson(getresponse);
    String actualaddress=js2.getString("address");
    
    System.out.println(actualaddress);
    Assert.assertEquals(newaddress, actualaddress);
    
    RestAssured.given()
    .pathParam("country", "Finland")
    .when()
        .get("http://restcountries.eu/rest/v1/name/{country}")
    .then().log().all().assertThat().statusCode(200)
        .body("capital", equalTo("Helsinki"));
  
     
	}
	
	

}
