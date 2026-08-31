package api.context;

import api.models.request.AccountRequestDTO;

public class AccountContext {

    private AccountRequestDTO account;
    private boolean created;
    private boolean deleted;
    private AccountRequestDTO pendingUpdateAccount;

    public AccountRequestDTO getAccount() {
        if (account == null) {
            throw new IllegalStateException(
                    "Account test data has not been prepared"
            );
        }

        return account;
    }

    public void setPendingUpdateAccount(AccountRequestDTO updateAccount){
        this.pendingUpdateAccount = updateAccount;
    }

    public void applyPendingToCurrentAccount(){
        this.account = pendingUpdateAccount;
        this.pendingUpdateAccount = null;
    }

    public void setAccount(AccountRequestDTO account) {
        this.account = account;
    }

    public boolean isCreated() {
        return created;
    }

    public void markAsCreated() {
        this.created = true;
        this.deleted = false;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void markAsDeleted() {
        this.deleted = true;
    }
}