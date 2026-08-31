package api.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MessageResponseDTO(
        @JsonProperty("responseCode")
        int responseCode,

        @JsonProperty("message")
        String message
) {
}
