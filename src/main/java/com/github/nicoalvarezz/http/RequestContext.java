package com.github.nicoalvarezz.http;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RequestContext {
    private String requestId;
    private final Map<String, String> httpHeaders; // Maybe this will have its onw implementation
    private boolean keepAlive;
    private boolean complete;

    public RequestContext() {
        this.requestId = UUID.randomUUID().toString();
        this.httpHeaders = new HashMap<>();
    }

    public String getRequestId() {
        return requestId;
    }

    public Map<String, String> getHttpHeaders() {
        return httpHeaders;
    }

    public void addHeader(String header, String value) {
        httpHeaders.put(header, value);
    }

    public void addAllHeaders(Map<String, String> headers) {
        httpHeaders.putAll(headers);
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
