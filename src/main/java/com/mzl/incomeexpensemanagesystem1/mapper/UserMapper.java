package com.mzl.incomeexpensemanagesystem1.mapper;

import com.mzl.incomeexpensemanagesystem1.entity.User;
import com.mzl.incomeexpensemanagesystem1.vo.UserRecordVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName :   AdminMapper
 * @Description: TODO
 * @Author: 21989
 * @CreateDate: 2020/6/5 16:36
 * @Version: 1.0
 */
public interface UserMapper {

    //通过用户名和密码查询用户是否存在
    public User queryUserByUser(User user);

    //通过用户名查询用户是否存在
    public User queryUserByUsername(String username);

    //用户注册
    public void insertUser(User user);

    //修改用户密码
    public void updatePasswordByUsername(User user);

    //查询总记录数
    int findUsersCount(User user);

    //查询总记录数
    List<User> findUsers(Map<String, Object> map);

    //查询要修改的用户原来信息
    User queryUserById(Integer uid);

    //编辑全部用户信息
    void editUserAll(User user);

    //进行修改用户信息
    void editUser(User user);

    /**
     * 删除用户
     * @param uid
     */
    void delUser(int uid);

    /**
     * 先查询所有用户
     * @return
     */
    List<User> selectAllUser();

    /**
     * 查询当前用户的当天的收入
     * @param uid
     * @param today
     * @return
     */
    Integer countIncome(int uid, String today);

    /**
     * 查询当前用户的当天的支出
     * @param uid
     * @param today
     * @return
     */
    Integer countExpense(int uid, String today);

    /**
     * 查询当前用户的本月的收入
     * @param uid
     * @param thisMonth
     * @return
     */
    Integer countMonthIncome(int uid, String thisMonth);

    /**
     * 查询当前用户的本月的支出
     * @param uid
     * @param thisMonth
     * @return
     */
    Integer countMonthExpense(int uid, String thisMonth);

    /**
     * 查询当前用户的本年的收入
     * @param uid
     * @param thisYear
     * @return
     */
    Integer countYearIncome(int uid, String thisYear);

    /**
     * 查询当前用户的本年的支出
     * @param uid
     * @param thisYear
     * @return
     */
    Integer countYearExpense(int uid, String thisYear);

}
