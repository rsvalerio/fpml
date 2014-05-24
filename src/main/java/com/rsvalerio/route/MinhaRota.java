package com.rsvalerio.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.Main;


public class MinhaRota extends RouteBuilder{

	public static void main(String... args) throws Exception {
		Main.main(args);
	}

    public void configure() {
        from("timer:timer.dev?period=5000&repeatCount=1").autoStartup("true")
        .to("direct:recuperar.arquivos")
        .end();
        
        from("direct:recuperar.arquivos")
        .setHeader(Exchange.HTTP_METHOD, constant("GET"))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/xml"))
		.setHeader(Exchange.HTTP_URI, simple("http://www.fpml.org/spec/fpml-5-3-6-rec-1/html/transparency/xml/products/rpt_ex50-gas-swap-prices-first-day.xml"))
		.to("http4://recuperar.arquivos?throwExceptionOnFailure=true")
		.marshal().xmljson()
		.unmarshal().json(JsonLibrary.Gson)
//		.log(LoggingLevel.DEBUG, "JSON: ${body} ")
		.log(LoggingLevel.DEBUG, "JSON: ${body[header][sentBy]} ")
		.end();

    }



}

