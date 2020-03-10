package entitys.gringotts;

import entitys.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "wizard_deposits")
public class Deposit extends BaseEntity {

    private String firstName;
    private String lastName;
    private String notes;
    private int age;
    private String magicWandCreator;
    private int magicWandSize;
    private String depositGroup;
    private LocalDate startDate;
    private BigDecimal amount;
    private double interest;
    private double charge;
    private LocalDate expirationDate;
    private boolean expired;

    public Deposit() {
    }

    @Column(name = "first_name", length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", length = 60)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "notes", length = 1000)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "magic_wand_creator", length = 100)
    public String getMagicWandCreator() {
        return magicWandCreator;
    }

    public void setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
    }

    @Column(name = "magic_wand_size")
    public int getMagicWandSize() {
        return magicWandSize;
    }

    public void setMagicWandSize(int magicWandSize) {
        this.magicWandSize = magicWandSize;
    }

    @Column(name = "deposit_group", length = 20)
    public String getDepositGroup() {
        return depositGroup;
    }

    public void setDepositGroup(String depositGroup) {
        this.depositGroup = depositGroup;
    }

    @Column(name = "deposit_start_date")
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Column(name = "deposit_amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(name = "deposit_interest")
    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    @Column(name = "deposit_charge")
    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    @Column(name = "deposit_expiration_date")
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Column(name = "is_deposit_expired")
    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
