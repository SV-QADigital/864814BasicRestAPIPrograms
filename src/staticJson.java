import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.ReuseableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class staticJson {
	@Test
	public void Addbook() throws IOException
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type","application/json")
		.body(getdatfromfile("C:\\Usersjeni\\Desktop\\Onboardingdocs\\Postman_APIContract\\staticJsondata.json"))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js=ReuseableMethods.rawtoJson(response);
		String Id=js.get("ID");
		System.out.println(Id);

}
	public static String getdatfromfile(String path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
