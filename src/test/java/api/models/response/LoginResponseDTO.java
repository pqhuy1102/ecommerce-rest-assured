package api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LoginResponseDTO(
        @JsonProperty("responseCode") int responseCode,
        @JsonProperty("message") String message,
        @JsonProperty("user") UserResponseDTO user) {
}
