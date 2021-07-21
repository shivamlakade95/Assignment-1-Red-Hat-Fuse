package com.mycompany;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleProcessor implements Processor {

    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void process(Exchange exchange) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String bodyText = (String)exchange.getIn().getBody();
        logger.info("Within Processor : body...."+bodyText);
        exchange.getIn().setBody("Hello at : "+sdf.format(new Date()), String.class);
        exchange.getIn().setHeader("NewHeader", "NewValue");
    }
}
