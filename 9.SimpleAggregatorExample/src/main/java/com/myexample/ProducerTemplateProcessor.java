package com.myexample;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;

public class ProducerTemplateProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        ProducerTemplate producerTemplate=exchange.getContext().createProducerTemplate();
        producerTemplate.sendBodyAndHeader("direct:start","A","myId","1");
        Thread.sleep(1000);
        producerTemplate.sendBodyAndHeader("direct:start","B","myId","1");
        Thread.sleep(1000);
        producerTemplate.sendBodyAndHeader("direct:start","F","myId","2");
        Thread.sleep(1000);
        producerTemplate.sendBodyAndHeader("direct:start","C","myId","1");
        Thread.sleep(1000);
        producerTemplate.sendBodyAndHeader("direct:start","G","myId","2");
        Thread.sleep(1000);
        producerTemplate.sendBodyAndHeader("direct:start","H","myId","2");
    }
}
