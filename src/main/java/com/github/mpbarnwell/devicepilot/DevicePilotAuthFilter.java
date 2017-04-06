package com.github.mpbarnwell.devicepilot;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class DevicePilotAuthFilter implements ClientRequestFilter {

    private static final String PREFIX = "Token ";

    private final String authToken;

    public DevicePilotAuthFilter(String authToken) {
        this.authToken = requireNonNull(authToken);
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        requestContext.getHeaders().add(HttpHeaders.AUTHORIZATION, PREFIX + authToken);
    }
}
