package run.weile.user.service.impl;

import java.util.List;
import run.weile.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.weile.user.mapper.WeileUserMapper;
import run.weile.user.domain.WeileUser;
import run.weile.user.service.IWeileUserService;

/**
 * 用户管理Service业务层处理
 *
 * @author songhaozhi
 * @date 2023-05-10
 */
@Service
public class WeileUserServiceImpl implements IWeileUserService
{
    @Autowired
    private WeileUserMapper weileUserMapper;

    /**
     * 查询用户管理
     *
     * @param userId 用户管理主键
     * @return 用户管理
     */
    @Override
    public WeileUser selectWeileUserByUserId(Long userId)
    {
        return weileUserMapper.selectWeileUserByUserId(userId);
    }

    /**
     * 查询用户管理列表
     *
     * @param weileUser 用户管理
     * @return 用户管理
     */
    @Override
    public List<WeileUser> selectWeileUserList(WeileUser weileUser)
    {
        return weileUserMapper.selectWeileUserList(weileUser);
    }

    /**
     * 新增用户管理
     *
     * @param weileUser 用户管理
     * @return 结果
     */
    @Override
    public int insertWeileUser(WeileUser weileUser)
    {
        weileUser.setCreateTime(DateUtils.getNowDate());
        return weileUserMapper.insertWeileUser(weileUser);
    }

    /**
     * 修改用户管理
     *
     * @param weileUser 用户管理
     * @return 结果
     */
    @Override
    public int updateWeileUser(WeileUser weileUser)
    {
        weileUser.setUpdateTime(DateUtils.getNowDate());
        return weileUserMapper.updateWeileUser(weileUser);
    }

    /**
     * 批量删除用户管理
     *
     * @param userIds 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deleteWeileUserByUserIds(Long[] userIds)
    {
        return weileUserMapper.deleteWeileUserByUserIds(userIds);
    }

    /**
     * 删除用户管理信息
     *
     * @param userId 用户管理主键
     * @return 结果
     */
    @Override
    public int deleteWeileUserByUserId(Long userId)
    {
        return weileUserMapper.deleteWeileUserByUserId(userId);
    }
}
