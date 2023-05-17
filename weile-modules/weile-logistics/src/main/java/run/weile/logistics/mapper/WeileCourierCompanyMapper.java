package run.weile.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import run.weile.logistics.domain.WeileCourierCompany;

import java.util.List;

/**
 * 快递公司代码Mapper接口
 *
 * @author songhaozhi
 * @date 2023-05-17
 */
public interface WeileCourierCompanyMapper extends BaseMapper<WeileCourierCompany> {
    /**
     * 查询快递公司代码
     *
     * @param id 快递公司代码主键
     * @return 快递公司代码
     */
    public WeileCourierCompany selectWeileCourierCompanyById(Long id);

    /**
     * 查询快递公司代码列表
     *
     * @param weileCourierCompany 快递公司代码
     * @return 快递公司代码集合
     */
    public List<WeileCourierCompany> selectWeileCourierCompanyList(WeileCourierCompany weileCourierCompany);

    /**
     * 新增快递公司代码
     *
     * @param weileCourierCompany 快递公司代码
     * @return 结果
     */
    public int insertWeileCourierCompany(WeileCourierCompany weileCourierCompany);

    /**
     * 修改快递公司代码
     *
     * @param weileCourierCompany 快递公司代码
     * @return 结果
     */
    public int updateWeileCourierCompany(WeileCourierCompany weileCourierCompany);

    /**
     * 删除快递公司代码
     *
     * @param id 快递公司代码主键
     * @return 结果
     */
    public int deleteWeileCourierCompanyById(Long id);

    /**
     * 批量删除快递公司代码
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWeileCourierCompanyByIds(Long[] ids);
}
