package run.weile.logistics.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import run.weile.common.core.annotation.Excel;
import run.weile.common.core.web.domain.BaseEntity;

/**
 * 快递公司代码对象 weile_courier_company
 *
 * @author songhaozhi
 * @date 2023-05-17
 */
public class WeileCourierCompany extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 快递公司名称
     */
    @Excel(name = "快递公司名称")
    private String courierCompanyName;

    /**
     * 快递公司代码
     */
    @Excel(name = "快递公司代码")
    private String courierCompanyCode;

    /**
     * 网址
     */
    @Excel(name = "网址")
    private String url;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCourierCompanyName(String courierCompanyName) {
        this.courierCompanyName = courierCompanyName;
    }

    public String getCourierCompanyName() {
        return courierCompanyName;
    }

    public void setCourierCompanyCode(String courierCompanyCode) {
        this.courierCompanyCode = courierCompanyCode;
    }

    public String getCourierCompanyCode() {
        return courierCompanyCode;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("courierCompanyName", getCourierCompanyName())
                .append("courierCompanyCode", getCourierCompanyCode())
                .append("url", getUrl())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
