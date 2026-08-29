package api.models.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SearchProductRequestDTO(
    @JsonProperty("search_product")
    String searchProduct
){}

