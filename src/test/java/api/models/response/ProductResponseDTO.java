package api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductResponseDTO(
        @JsonProperty("id")
        String id,

        @JsonProperty("name")
        String name,

        @JsonProperty("price")
        String price,

        @JsonProperty("brand")
        String brand,

        @JsonProperty("category")
        CategoryResponseDTO category
) {
}
