package com.mzl.incomeexpensemanagesystem1.service;

import java.util.Map;

/**
 * @InterfaceName :   FinacialAnalysisService
 * @Description: 财务分析
 * @Author: 21989
 * @CreateDate: 2020/7/5 10:52
 * @Version: 1.0
 */
public interface FinacialAnalysisService {

    //月收入的记录数
    int findMonthIncomeRecordCount(Map<String, String> paramMap);

    //月支出的记录数
    int findMonthSpendRecordCount(Map<String, String> paramMap);

    //月收入总金额
    int findMonthIncomeMoney(Map<String, String> paramMap);

    //月的支出总额
    int findMonthSpendMoney(Map<String, String> paramMap);
}
