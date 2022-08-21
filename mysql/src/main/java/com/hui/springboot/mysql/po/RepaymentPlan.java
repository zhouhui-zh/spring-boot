package com.hui.springboot.mysql.po;

/**
 * 还款日
 */
public class RepaymentPlan {
    private Long id;
    private Long cardId;
    //出账月
    private String month;
    //账单日
    private String billDate;
    //最后还款日
    private String LatestRepaymentDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public String getBillDate() {
    return billDate;
  }

  public void setBillDate(String billDate) {
    this.billDate = billDate;
  }

  public String getLatestRepaymentDate() {
    return LatestRepaymentDate;
  }

  public void setLatestRepaymentDate(String latestRepaymentDate) {
    LatestRepaymentDate = latestRepaymentDate;
  }
}
