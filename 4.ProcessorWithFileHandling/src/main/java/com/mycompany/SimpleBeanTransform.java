package com.mycompany;
import org.apache.camel.Exchange;

public class SimpleBeanTransform {
	
	//Logger logger  = Logger.;
	
	public static void map(Exchange exchange) {
		String custom = exchange.getIn().getBody().toString();
		System.out.println("Body bean received at: "+ custom );
		custom = "Modified at: "+ custom;
		exchange.getIn().setBody(custom);
		exchange.getIn().setHeader("TestHeader", "TestValue");
		
	}

}
