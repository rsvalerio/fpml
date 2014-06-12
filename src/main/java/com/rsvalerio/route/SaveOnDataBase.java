package com.rsvalerio.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class SaveOnDataBase extends RouteBuilder {

    public static void main(String[] args) throws Exception {
	Main.main(args);
    }

    @Override
    public void configure() throws Exception {
	// 5 - save on database
	from("file:data/json?noop=true")
		.convertBodyTo(String.class)
		.process(new Processor() {

		    @Override
		    public void process(Exchange ex) throws Exception {
			DBObject dbObject = (DBObject) JSON.parse((String) ex
				.getIn().getBody());
			ex.getOut().setBody(dbObject);
		    }
		})
		.to("mongodb:myDb?database=poc&collection=trades&operation=save");
    }
}
