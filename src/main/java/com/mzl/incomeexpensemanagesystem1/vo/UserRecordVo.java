package com.mzl.incomeexpensemanagesystem1.vo;

import com.mzl.incomeexpensemanagesystem1.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName :   UserRecordVo
 * @Description: TODO
 * @Author: v_ktlema
 * @CreateDate: 2022/4/2 17:35
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRecordVo extends User implements Serializable {

    //花费的金额
    private Integer expenseNum;

    //收入的金额
    private Integer incomeNum;

    //总金额
    private Integer totalNum;

}
