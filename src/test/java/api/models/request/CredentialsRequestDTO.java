package api.models.request;

public record CredentialsRequestDTO(
        String email,
        String password) {
}
