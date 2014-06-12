package com.rsvalerio.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class ExportFromDBRouteBuilder extends RouteBuilder {

    public static void main(String[] args) throws Exception {
	Main.main(args);
    }

    @Override
    public void configure() throws Exception {
	// 1 - trigger the process
	from("timer:timer.findAll?period=5000&repeatCount=10")
		.autoStartup("true").to("direct:findAll").end();

	// 2 - Find from DB
	from("direct:findAll")
		.to("mongodb:myDb?database=poc&collection=trades&operation=findAll")
		.split(body())
		.log(LoggingLevel.DEBUG, "Json from DB ${body}")
		.process(new Processor() {

		    @Override
		    public void process(Exchange ex) throws Exception {
			System.out.println(ex.getIn().getBody());
			DBObject object = ex.getIn().getBody(DBObject.class);
			ex.getOut().setBody(JSON.serialize(object));
		    }
		})
		.to("file:data/fromdb?fileName=Trade${date:now:yyyyMMddHHmm}.xml")
		.log(LoggingLevel.DEBUG, "XML: ${body} ").end();

    }

}
