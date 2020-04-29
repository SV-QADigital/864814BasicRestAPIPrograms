import  static io.restassured.RestAssured.*;

import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;

import POJO.Api;
import POJO.GetCourse;

import org.openqa.selenium.WebDriver;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
public class OAuthTest {

	public static void main(String[] args) {
		//Code to getthe accesstoken
		
		String url="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FzAEDthUsBr93_clSqIiysaRYE3LEKrT77HR2qBAH05buvcxoY3C116MhkFywPsMqikwAP8Y0z8wA3VNC7V2Wlx4&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		 
		  String partialcode=url.split("code=")[1];
		  
		  String code=partialcode.split("&scope")[0];
		  
		  
		  String accesstokenresponse=
				  
				  given()
				  
				  .urlEncodingEnabled(false)
				  
		          .queryParams("code",code)
		          
		          .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		          
		          .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		          
		          .queryParams("grant_type","authorization_code")
		          
		          .queryParams("state", "verifyfjdss")
		          
		          .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
		          
		          .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		          
		          .when().log().all()
		          
		                  .post("https://www.googleapis.com/oauth2/v4/token").asString();
		  
		  System.out.println("Accesstoken: "+accesstokenresponse);
		  
		 //System.out.println(code);
		  
		 JsonPath js=new JsonPath(accesstokenresponse);
		 
		 String access_token= js.get("access_token");
		 
		 System.out.println(access_token);
		 
		String accessres= given().contentType("application/json")
				
		 .queryParams("access_token", access_token).expect().defaultParser(Parser.JSON)
		 
		 .when() 
		        .get("https://rahulshettyacademy.com/getCourse.php")
		 
		.asString();
		
		System.out.println(accessres);
		 
		 
		 
		 
		 
		  
		

		GetCourse response=given().queryParam("access_token",access_token).expect().defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		 System.out.println(response.getLinkdIn());
		 //display first coursetitle
		 System.out.println(response.getCourses().getApi().get(1).getCourseTitle());
		 //display coursetitles
		 List<Api>apiCourses= response.getCourses().getApi();
		 for(int i=0;i<apiCourses.size();i++)
		 {
			 if(apiCourses.get(i).getCourseTitle().equals("SoapUI Webservices Testing"))
			 {
				 apiCourses.get(i).getPrice();
			 }
		 }
		

	}

}
