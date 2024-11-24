package com.github.nicoalvarezz.http;

import com.github.nicoalvarezz.network.UpstreamConnection;

public class ResponseContext {
    private final String requestId;
    private boolean complete;
    private final UpstreamConnection upstreamConnection;

    public ResponseContext(String requestId, UpstreamConnection upstreamConnection) {
        this.requestId = requestId;
        this.upstreamConnection = upstreamConnection
    }
}
