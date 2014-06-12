package com.rsvalerio.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

public class MinhaRota01 extends RouteBuilder {

    public static void main(String... args) throws Exception {
	Main.main(args);
    }

    @Override
    public void configure() {
	from("file:data/inbox").to("file:data/outbox");
    }

}
