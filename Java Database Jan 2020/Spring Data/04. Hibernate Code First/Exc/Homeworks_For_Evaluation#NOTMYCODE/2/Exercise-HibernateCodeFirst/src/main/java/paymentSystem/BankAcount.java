package paymentSystem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bank_accounts")
public class BankAcount extends BaseEntity {
    private String bankName;
    private String SwiftCode;

    public BankAcount() {
    }

    @Column(name = "bank_name")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(name = "swift_code")
    public String getSwiftCode() {
        return SwiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        SwiftCode = swiftCode;
    }
}
