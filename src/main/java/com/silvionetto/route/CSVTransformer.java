package com.silvionetto.route;

import com.silvionetto.entity.LegalEntity;
import com.silvionetto.entity.Trade;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvionetto on 5/12/16.
 */
public class CSVTransformer extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:csv.transformer")
                .unmarshal()
                .csv()
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        List<List<String>> data = (List<List<String>>) exchange.getIn().getBody();
                        List<Trade> trades = new ArrayList<Trade>();
                        for (List<String> line : data) {
                            String externalId = line.get(0);
                            Double amount = Double.valueOf(line.get(1));
                            LegalEntity legalEntity = new LegalEntity(line.get(2));
                            Trade trade = new Trade(externalId, amount, legalEntity);
                            trades.add(trade);
                        }
                        exchange.getOut().setBody(trades);
                    }
                })
        ;

    }
}
