package api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public record CategoryResponseDTO(
        @JsonProperty("usertype")
        UserTypeResponseDTO usertype,

        @JsonProperty("category")
        String category
) {
}
