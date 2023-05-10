package run.weile.user.mapper;

import run.weile.user.domain.WeileUser;

import java.util.List;

/**
 * 用户管理Mapper接口
 *
 * @author songhaozhi
 * @date 2023-05-10
 */
public interface WeileUserMapper {
    /**
     * 查询用户管理
     *
     * @param userId 用户管理主键
     * @return 用户管理
     */
    public WeileUser selectWeileUserByUserId(Long userId);

    /**
     * 查询用户管理列表
     *
     * @param weileUser 用户管理
     * @return 用户管理集合
     */
    public List<WeileUser> selectWeileUserList(WeileUser weileUser);

    /**
     * 新增用户管理
     *
     * @param weileUser 用户管理
     * @return 结果
     */
    public int insertWeileUser(WeileUser weileUser);

    /**
     * 修改用户管理
     *
     * @param weileUser 用户管理
     * @return 结果
     */
    public int updateWeileUser(WeileUser weileUser);

    /**
     * 删除用户管理
     *
     * @param userId 用户管理主键
     * @return 结果
     */
    public int deleteWeileUserByUserId(Long userId);

    /**
     * 批量删除用户管理
     *
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWeileUserByUserIds(Long[] userIds);
}
