package entitys.payment_system;

import entitys.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "billing_detail")
public class Billing_Detail extends BaseEntity {

    private String number;
    private String owner;
    private User user;
    private Set<Bank_Account> bank_accounts;
    private Set<Card> cards;

    public Billing_Detail() {
    }

    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "billing_detail", targetEntity = Bank_Account.class)
    public Set<Bank_Account> getBank_accounts() {
        return bank_accounts;
    }

    public void setBank_accounts(Set<Bank_Account> bank_accounts) {
        this.bank_accounts = bank_accounts;
    }

    @OneToMany(mappedBy = "billing_detail", targetEntity = Card.class)
    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }
}
