

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReuseableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	@Test(dataProvider="Bookdetails")
	public void Addbook(String isbn,String aisle  )
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type","application/json")
		.body(payload.Addbook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js=ReuseableMethods.rawtoJson(response);
		String Id=js.get("ID");
		System.out.println(Id);
		
		//Delete book
		String getdeleteresponse=given().header("Content-Type","application/json")
		.body(payload.deletebook(Id))
		.when().delete("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js1=ReuseableMethods.rawtoJson(getdeleteresponse);
		String msg=js.getString("msg");
		System.out.println(msg);
		
	}
	@DataProvider(name="Bookdetails")
	public  Object[][] getData()
	{
		 return new Object[][] {{"absa1","238"},{"ajh1","239"},{"ahg","240"}};
	}

}
