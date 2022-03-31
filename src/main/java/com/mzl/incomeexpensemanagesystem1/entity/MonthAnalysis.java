package com.mzl.incomeexpensemanagesystem1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @ClassName :   MonthAnalysis
 * @Description: 月分析实体类
 * @Author: 21989
 * @CreateDate: 2020/7/5 11:57
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthAnalysis implements Serializable {

    private int incomeRecordCount;  //收入记录数
    private int spendsRecordCount;  //支出记录数
    private int incomeMoney;  //收入金额
    private int spendsMoney; //支出金额
    private int allMoney;   //总金额

    public void setIncomeRecordCount(int incomeRecordCount) {
        this.incomeRecordCount = incomeRecordCount;
    }

    public void setSpendsRecordCount(int spendsRecordCount) {
        this.spendsRecordCount = spendsRecordCount;
    }

    public void setSpendsMoney(int spendsMoney) {
        this.spendsMoney = spendsMoney;
    }

    public void setIncomeMoney(int incomeMoney) {
        this.incomeMoney = incomeMoney;
    }


    public void setAllMoney(int allMoney) {
        this.allMoney = allMoney;
    }

    public int getIncomeRecordCount() {
        return incomeRecordCount;
    }

    public int getSpendsRecordCount() {
        return spendsRecordCount;
    }

    public int getSpendsMoney() {
        return spendsMoney;
    }

    public int getIncomeMoney() {
        return incomeMoney;
    }

    public int getAllMoney() {
        return allMoney;
    }

    @Override
    public String toString() {
        return "MonthAnalysis{" +
                "incomeRecordCount=" + incomeRecordCount +
                ", spendsRecordCount=" + spendsRecordCount +
                ", incomeMoney=" + incomeMoney +
                ", spensdMoney=" + spendsMoney +
                ", allMoney=" + allMoney +
                '}';
    }
}
