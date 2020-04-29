import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class sumvalidate {
	
	@Test
	public void sumcourse()
	{
		int sum=0;
		 int purchaseamount = 0;
		JsonPath js=new JsonPath(payload.coursePrice());
		
		int count=  js.getInt("courses.size()");
		for(int i=0;i<count;i++)
		  {
			 String coursetitle= js.get("courses["+i+"].title");
			 System.out.println("coursetitle:"+coursetitle);
			  purchaseamount= js.getInt("dashboard.purchaseAmount");
			 System.out.println("Price:"+js.get("courses["+i+"].price").toString());
			 int price=js.get("courses["+i+"].price");
			 int copies=js.getInt("courses["+i+"].copies");
			 sum=sum+(price*copies);
			 
		  }
		System.out.println(sum);
		Assert.assertEquals(sum, purchaseamount);
		
	}
}
