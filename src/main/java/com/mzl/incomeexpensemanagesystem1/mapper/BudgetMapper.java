package com.mzl.incomeexpensemanagesystem1.mapper;

import com.mzl.incomeexpensemanagesystem1.entity.Budget;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName :   BudgetMapper
 * @Description: 预算dao层接口
 * @Author: 21989
 * @CreateDate: 2020/7/5 21:19
 * @Version: 1.0
 */
public interface BudgetMapper {

    //查找当前月份是否存在预算
    Budget findBudget(Budget budget);

    //添加预算
    void addBudget(Budget budget);

    //编辑预算
    void editBudget(Budget budget);

    //删除预算
    void deleteBudget(int wid);
}
