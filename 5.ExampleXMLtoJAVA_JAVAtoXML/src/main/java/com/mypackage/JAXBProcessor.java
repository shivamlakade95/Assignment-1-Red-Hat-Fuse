package com.mypackage;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBProcessor implements Processor {

    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void process(Exchange exchange) throws Exception {
        PurchaseOrder order= new PurchaseOrder();
        order.setName("Shivam Lakade");
        order.setPrice(9999);
        order.setAmount(8);

        logger.info("In Marshaling - PurchaseOrder :");
        unmarshall();
        exchange.getIn().setBody(order);
    }


    private static final String INBOX ="file:///home/slakade/Downloads/etc/output/ID-slakade-pnq-csb-1626679209519-13-4\n";
    public void unmarshall()
    {
        try{
            logger.info("In Unmarshal :"+ INBOX);

            JAXBContext context = JAXBContext.newInstance(PurchaseOrder.class);
            Unmarshaller ums=context.createUnmarshaller();
            PurchaseOrder order2=(PurchaseOrder) ums.unmarshal(new File("/home/slakade/Downloads/etc/output/ID-slakade-pnq-csb-1626679209519-13-4"));
            //
            logger.info("Name :"+ order2.getName());
            logger.info("Price :"+ order2.getPrice());
            logger.info("Amount :"+ order2.getAmount());
        }
        catch (Exception e)
        {
            logger.info("Exception occurred :"+ e);
        }

    }

}
