package com.byshu.websocket;


import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketTest extends WebSocketClient {


    public WebSocketTest(URI serverUri) {
        super(serverUri);
    }


    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.err.println("onOpen..");
    }

    @Override
    public void onMessage(String s) {
        System.err.println("onMessage : " + s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.err.println("onClose..");
    }

    @Override
    public void onError(Exception e) {
        System.err.println("onError..");
    }

    public static void main(String[] args) throws URISyntaxException {
        WebSocketTest webSocketClient = new WebSocketTest(new URI("ws://127.0.0.1:8080/websocket/12/owner"));
        webSocketClient.connect();
    }
}
