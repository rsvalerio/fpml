package com.rsvalerio.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

import com.mongodb.util.JSON;

public class XMLTransformer extends RouteBuilder {

    public static void main(String[] args) throws Exception {
	Main.main(args);
    }

    @Override
    public void configure() throws Exception {

	// 3 - Convert Json to XML
	from("file:data/fromdb?noop=true").autoStartup("true")
		.convertBodyTo(String.class).process(new Processor() {

		    @Override
		    public void process(Exchange ex) throws Exception {
			String json = JSON.serialize(ex.getIn().getBody());
			ex.getOut().setBody(json);
		    }
		}).to("file:data/xml").end();
    }
}
