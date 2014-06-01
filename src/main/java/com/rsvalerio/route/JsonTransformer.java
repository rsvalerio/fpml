package com.rsvalerio.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

public class JsonTransformer extends RouteBuilder {

    public static void main(String[] args) throws Exception {
	Main.main(args);
    }

    @Override
    public void configure() throws Exception {
	from("file:data/received?noop=true").marshal().xmljson()
		.to("file:data/json").end();
    }
}
