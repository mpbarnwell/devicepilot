package com.github.mpbarnwell.devicepilot;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.OptionalDouble;

@Value.Immutable
@JsonSerialize(as = ImmutableDeviceRecord.class)
@JsonDeserialize(as = ImmutableDeviceRecord.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface DeviceRecord {

    @JsonProperty("$id")
    String id();

    OptionalDouble latitude();
    OptionalDouble longitude();
    Optional<String> label();
    OptionalDouble temperature();

}
