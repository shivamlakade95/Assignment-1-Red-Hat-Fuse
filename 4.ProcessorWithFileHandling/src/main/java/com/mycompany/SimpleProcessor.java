package com.mycompany;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleProcessor extends RouteBuilder implements Processor {

    Logger logger = Logger.getLogger(this.getClass());

    public void configure() {
        logger.info("Within file component---------->");
       /* from("direct:report").setHeader(Exchange.FILE_NAME, constant("${date:now:yyyyMMdd-hh:mm:ss}.txt"))
                .to("file:/home/slakade/Downloads/etc/?fileName=${date:now:yyyyMMdd-hh:mm:ss}.txt");*/

        /*from("file:Downloads/data/inbox?fileName=${date:now:yyyyMMdd-hh:mm:ss}.txt")
                .to("stream.out");*/
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        this.configure();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String bodyText = (String)exchange.getIn().getBody();
        logger.info("Within Processor : body...."+bodyText);
        exchange.getIn().setBody("Hello at : "+sdf.format(new Date()), String.class);
        exchange.getIn().setHeader("NewHeader", "NewValue");
    }
}
