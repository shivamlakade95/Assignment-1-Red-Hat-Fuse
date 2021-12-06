package com.mycompany.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.ThrottlingExceptionHalfOpenHandler;
import org.apache.camel.impl.ThrottlingExceptionRoutePolicy;

public class CamelRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		int threshold = 2;
		long failureWindow = 20000;
		long halfOpenAfter = 30000;
		//boolean keepOpen = false;
		ThrottlingExceptionRoutePolicy policy = new ThrottlingExceptionRoutePolicy(threshold, failureWindow, halfOpenAfter, null);

		System.out.println("Before route: "+policy.getKeepOpen());

		//Router start
		from("timer:foo?period=5000")
				.routePolicy(policy)
				.log("Done processing")
				.to("http4://localhost:8080/__admin/")
				.log("Done processing ${body}")
				/*.to("log:foo?groupSize=10").to("mock:result");*/
				.end();

		//end of router
	}

}
