package entities_task5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bank_accounts")
public class BankAccount extends BillingDetail {
    private String bankName;
    private String swiftCode;

    public BankAccount() {
    }
    @Column(name = "bank_name", nullable = false)
    public String getName() {
        return bankName;
    }

    public void setName(String name) {
        this.bankName = name;
    }
    @Column(name = "swift_code", nullable = false)
    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String SWIFTCode) {
        this.swiftCode = SWIFTCode;
    }
}
