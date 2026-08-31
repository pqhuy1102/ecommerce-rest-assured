package api.data;

import api.models.request.AccountRequestDTO;

import java.util.UUID;

public final class AccountDataFactory {

    private AccountDataFactory() {
    }

    public static AccountRequestDTO createUniqueAccount() {
        String uniqueId = UUID.randomUUID()
                .toString()
                .substring(0, 8);

        return new AccountRequestDTO(
                "Huy Test",
                "huy." + uniqueId + "@example.com",
                "Test@123456",
                "Mr",
                "10",
                "5",
                "2000",
                "Huy",
                "Pham",
                "QE Automation",
                "123 Nguyen Hue",
                "District 1",
                "Vietnam",
                "700000",
                "Ho Chi Minh",
                "Ho Chi Minh City",
                "0901234567"
        );
    }

    public static AccountRequestDTO createUpdatedAccount(
            AccountRequestDTO currentAccount
    ) {
        return new AccountRequestDTO(
                "Huy Updated",
                currentAccount.email(),
                currentAccount.password(),
                currentAccount.title(),
                currentAccount.birthDate(),
                currentAccount.birthMonth(),
                currentAccount.birthYear(),
                currentAccount.firstname(),
                currentAccount.lastname(),
                "Updated Company",
                currentAccount.address1(),
                currentAccount.address2(),
                currentAccount.country(),
                currentAccount.zipcode(),
                currentAccount.state(),
                currentAccount.city(),
                currentAccount.mobileNumber()
        );
    }
}