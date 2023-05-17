package run.weile.logistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.weile.common.core.utils.DateUtils;
import run.weile.common.security.utils.SecurityUtils;
import run.weile.logistics.domain.WeileCourierCompany;
import run.weile.logistics.mapper.WeileCourierCompanyMapper;
import run.weile.logistics.service.IWeileCourierCompanyService;

import java.util.List;

/**
 * 快递公司代码Service业务层处理
 *
 * @author songhaozhi
 * @date 2023-05-17
 */
@Service
public class WeileCourierCompanyServiceImpl extends ServiceImpl<WeileCourierCompanyMapper,WeileCourierCompany>
        implements IWeileCourierCompanyService {
    @Autowired
    private WeileCourierCompanyMapper weileCourierCompanyMapper;

    /**
     * 查询快递公司代码
     *
     * @param id 快递公司代码主键
     * @return 快递公司代码
     */
    @Override
    public WeileCourierCompany selectWeileCourierCompanyById(Long id) {
        return weileCourierCompanyMapper.selectWeileCourierCompanyById(id);
    }

    /**
     * 查询快递公司代码列表
     *
     * @param weileCourierCompany 快递公司代码
     * @return 快递公司代码
     */
    @Override
    public List<WeileCourierCompany> selectWeileCourierCompanyList(WeileCourierCompany weileCourierCompany) {
        return weileCourierCompanyMapper.selectWeileCourierCompanyList(weileCourierCompany);
    }

    /**
     * 新增快递公司代码
     *
     * @param weileCourierCompany 快递公司代码
     * @return 结果
     */
    @Override
    public int insertWeileCourierCompany(WeileCourierCompany weileCourierCompany) {
        weileCourierCompany.setCreateTime(DateUtils.getNowDate());
        weileCourierCompany.setCreateBy(SecurityUtils.getUsername());
        return weileCourierCompanyMapper.insertWeileCourierCompany(weileCourierCompany);
    }

    /**
     * 修改快递公司代码
     *
     * @param weileCourierCompany 快递公司代码
     * @return 结果
     */
    @Override
    public int updateWeileCourierCompany(WeileCourierCompany weileCourierCompany) {
        weileCourierCompany.setUpdateTime(DateUtils.getNowDate());
        weileCourierCompany.setUpdateBy(SecurityUtils.getUsername());
        return weileCourierCompanyMapper.updateWeileCourierCompany(weileCourierCompany);
    }

    /**
     * 批量删除快递公司代码
     *
     * @param ids 需要删除的快递公司代码主键
     * @return 结果
     */
    @Override
    public int deleteWeileCourierCompanyByIds(Long[] ids) {
        return weileCourierCompanyMapper.deleteWeileCourierCompanyByIds(ids);
    }

    /**
     * 删除快递公司代码信息
     *
     * @param id 快递公司代码主键
     * @return 结果
     */
    @Override
    public int deleteWeileCourierCompanyById(Long id) {
        return weileCourierCompanyMapper.deleteWeileCourierCompanyById(id);
    }
}
