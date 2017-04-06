package com.github.mpbarnwell.devicepilot;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class DevicePilotClient {

    private static final GenericType<Map<String,String>> STRING_MAP_TYPE = new GenericType<Map<String,String>>() {};

    private final WebTarget target;

    public static void main(String[] args) {
        new DevicePilotClient(new JerseyClientBuilder().build(), "API_KEY");
    }

    public DevicePilotClient(JerseyClient client, String apiKey) {
        this.target = client.target("https://api.devicepilot.com")
                .register(new DevicePilotAuthFilter(apiKey));

        DeviceRecord record = ImmutableDeviceRecord.builder()
                .id("anything")
                .label("test")
                .latitude(0)
                .longitude(0)
                .temperature(10)
                .build();

        String urn = create(record);
        delete(urn);
    }

    public String create(DeviceRecord record) {
        Response response = target.path("devices")
                .request()
                .post(Entity.json(record));

        if (response.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL)) {
            throw new WebApplicationException(response);
        }

        return response.readEntity(STRING_MAP_TYPE).get("$urn");
    }

    public void delete(String urn) {
        Response response = target.path(urn)
                .request()
                .delete();
        if (response.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL)) {
            throw new WebApplicationException(response);
        }
    }

}
