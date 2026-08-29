package api.models.response;


import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiErrorResponseDTO(
        @JsonProperty("responseCode")
        int responseCode,

        @JsonProperty("message")
        String message
) {
}
