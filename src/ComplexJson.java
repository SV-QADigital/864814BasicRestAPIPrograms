import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson 
{

	public static void main(String[] args) {
		
		int sum=0;
		JsonPath js=new JsonPath(payload.coursePrice());
	     //Print No of courses returned by API
	   int count=  js.getInt("courses.size()");
	   System.out.println(count);
	  int purchaseamount= js.getInt("dashboard.purchaseAmount");
	  System.out.println(purchaseamount);
	  String firstcoursetitle=js.get("courses[0].title");
	  System.out.println(firstcoursetitle);
	  for(int i=0;i<count;i++)
	  {
		 String coursetitle= js.get("courses["+i+"].title");
		 System.out.println("coursetitle:"+coursetitle);
		 System.out.println("Price:"+js.get("courses["+i+"].price").toString());
		 int price=js.get("courses["+i+"].price");
		 int copies=js.getInt("courses["+i+"].copies");
		 sum=sum+(price*copies);
		 
	  }
	  
	  System.out.println("Verify if Sum of all Course prices matches with Purchase Amount:");
	  Assert.assertEquals(sum, purchaseamount);
	  System.out.println("Print no of copies sold by RPA Course:");
	  for(int i=0;i<count;i++)
	  {
		 String coursetitle= js.get("courses["+i+"].title");
		 if(coursetitle.equals("RPA"))
		 {
			 int copies=js.getInt("courses["+i+"].copies");
			 System.out.print(copies);
			 break;
		 }
		  //sum=sum+price;
		 
	  }
	  //System.out.println(sum);
	  //Assert.assertEquals(sum, purchaseamount);
	 
	     
	}
     
}
