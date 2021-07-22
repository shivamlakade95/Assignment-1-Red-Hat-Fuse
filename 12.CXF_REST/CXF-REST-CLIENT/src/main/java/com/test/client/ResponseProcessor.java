package com.test.client;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.jboss.fuse.quickstarts.cxf.rest.Customer;

public class ResponseProcessor implements Processor {

	Logger logger = Logger.getLogger(this.getClass());
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		Customer customer = exchange.getIn().getBody(Customer.class);
		logger.info("Id:"+ customer.getId());
		
	    logger.info("Customer details:"+ customer.toString());
	}

}
