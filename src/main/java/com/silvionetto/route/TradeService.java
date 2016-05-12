package com.silvionetto.route;

import com.silvionetto.entity.LegalEntity;
import com.silvionetto.entity.Trade;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.util.*;

/**
 * Created by silvionetto on 5/12/16.
 */
public class TradeService  extends RouteBuilder {

    private static int count = 0;

    private Map<Integer, Trade> dataBase = new HashMap<>();

    {
        dataBase.put(count++, new Trade("X1", 10.00, new LegalEntity("SilvioNetto")));
        dataBase.put(count++, new Trade("X2", 20.00, new LegalEntity("Anatolli")));
    }

    public static void main(String[] args) throws Exception {
        org.apache.camel.spring.Main.main(args);
    }

    @Override
    public void configure() throws Exception {
        from("file:data/csv/inbox?fileName=Example.csv&noop=true")
                .to("direct:csv.transformer")
                .to("direct:update.trade")
                .to("direct:show.trades")
        ;

        from("direct:update.trade")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        List<Trade> trades = (List<Trade>) exchange.getIn().getBody();
                        for (Trade trade: trades) {
                            updateTrade(trade);
                        }
                    }
                })
        ;

        from("direct:show.trades")
            .process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {
                    Iterator<Trade> trades = dataBase.values().iterator();
                    Trade t = null;
                    while(trades.hasNext()) {
                        t = trades.next();
                        System.out.println(t);
                    }
                }
            })
        ;
    }

    public void updateTrade(Trade trade) {
        Iterator<Trade> trades = dataBase.values().iterator();
        Trade t = null;
        boolean exist = false;
        while(trades.hasNext()) {
            t = trades.next();
            if (t.getExternalId().equals(trade.getExternalId())) {
                t.setDate(Calendar.getInstance());
                t.setAmount(trade.getAmount());
                t.setLegalEntity(trade.getLegalEntity());
                exist = true;
            }
        }
        if (!exist) {
            dataBase.put(count++, trade);
        }
    }
}
