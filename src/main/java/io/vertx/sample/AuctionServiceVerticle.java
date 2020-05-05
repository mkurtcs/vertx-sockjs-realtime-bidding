package io.vertx.sample;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.BridgeEventType;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

public class AuctionServiceVerticle extends AbstractVerticle {

    private static final Logger logger = LoggerFactory.getLogger(AuctionServiceVerticle.class);

    @Override
    public void start() {

    }

    private SockJSHandler eventBusHandler() {
        BridgeOptions options = new BridgeOptions()
                .addOutboundPermitted(new PermittedOptions().setAddressRegex("auction\\.[0-9]+"));
        return SockJSHandler.create(vertx).bridge(options, event -> {
            if(event.type() == BridgeEventType.SOCKET_CREATED) {
                logger.info("A socket was created.");
            }
            event.complete();
        });
    }

}
