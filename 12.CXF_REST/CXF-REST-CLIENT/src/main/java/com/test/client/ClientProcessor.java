package com.test.client;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ClientProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Object[] obj = {"123"};
		//String id="123";
		
		exchange.getIn().setBody(obj);
	}

}
