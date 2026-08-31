package api.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountRequestDTO(
        String name,
        String email,
        String password,
        String title,

        @JsonProperty("birth_date")
        String birthDate,

        @JsonProperty("birth_month")
        String birthMonth,

        @JsonProperty("birth_year")
        String birthYear,

        String firstname,
        String lastname,
        String company,
        String address1,
        String address2,
        String country,
        String zipcode,
        String state,
        String city,

        @JsonProperty("mobile_number")
        String mobileNumber
) {
}
