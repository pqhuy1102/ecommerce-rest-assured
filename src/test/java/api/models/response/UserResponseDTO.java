package api.models.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserResponseDTO(
        @JsonProperty("id") Long id,
        @JsonProperty("email") String email,
        @JsonProperty("name") String name,
        @JsonProperty("role") String role,
        @JsonProperty("title") String title,
        @JsonProperty("birthDate") Instant birthDate,
        @JsonProperty("firstName") String firstName,
        @JsonProperty("lastName") String lastName,
        @JsonProperty("company") String company,
        @JsonProperty("address1") String address1,
        @JsonProperty("address2") String address2,
        @JsonProperty("country") String country,
        @JsonProperty("state") String state,
        @JsonProperty("city") String city,
        @JsonProperty("zipcode") String zipcode,
        @JsonProperty("mobileNumber") String mobileNumber,
        @JsonProperty("newsletter") boolean newsletter,
        @JsonProperty("specialOffers") boolean specialOffers,
        @JsonProperty("createdAt") Instant createdAt
) {
}
