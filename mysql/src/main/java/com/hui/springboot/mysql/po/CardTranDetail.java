package com.hui.springboot.mysql.po;

import java.math.BigDecimal;
import java.util.Date;

public class CardTranDetail {
    private Integer id;

    private Date tranDate;

    private Integer seq;

    private Integer cardId;

    private Integer poundage;

    private Boolean tranType;

    private BigDecimal amount;

    private BigDecimal tranAmount;

    private Integer availableCredit;

    private Date cycRepaymentDate;

    private BigDecimal cash;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getPoundage() {
        return poundage;
    }

    public void setPoundage(Integer poundage) {
        this.poundage = poundage;
    }

    public Boolean getTranType() {
        return tranType;
    }

    public void setTranType(Boolean tranType) {
        this.tranType = tranType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(BigDecimal tranAmount) {
        this.tranAmount = tranAmount;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public Integer getAvailableCredit() {
        return availableCredit;
    }

    public void setAvailableCredit(Integer availableCredit) {
        this.availableCredit = availableCredit;
    }

    public Date getCycRepaymentDate() {
        return cycRepaymentDate;
    }

    public void setCycRepaymentDate(Date cycRepaymentDate) {
        this.cycRepaymentDate = cycRepaymentDate;
    }


}