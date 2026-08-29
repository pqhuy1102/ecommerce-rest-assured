package api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductListResponseDTO(
        @JsonProperty("responseCode")
        int responseCode,

        @JsonProperty("products")
        List<ProductResponseDTO> products
) {
}
