package entities_task5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "credit_cards")
public class CreditCard extends  BillingDetail {
    private String cardType;
    private String expirationMonth;
    private String expirationYear;

    public CreditCard() {
    }

    @Column(name = "cred_type", nullable = false)
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    @Column(name = "expiration_month", nullable = false)
    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }
    @Column(name = "expiration_year", nullable = false)
    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }
}
