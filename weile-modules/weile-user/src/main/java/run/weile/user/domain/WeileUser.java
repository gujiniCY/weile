package run.weile.user.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import run.weile.common.core.annotation.Excel;
import run.weile.common.core.web.domain.BaseEntity;

/**
 * 用户管理对象 weile_user
 *
 * @author songhaozhi
 * @date 2023-05-10
 */
public class WeileUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 小程序openId */
    @Excel(name = "小程序openId")
    private String miniOpenId;

    /** AppOpenId */
    @Excel(name = "AppOpenId")
    private String appOpenId;

    /** unionid */
    @Excel(name = "unionid")
    private String unionId;

    /** 所在国家 */
    @Excel(name = "所在国家")
    private String country;

    /** 所在省份 */
    @Excel(name = "所在省份")
    private String province;

    /** 所在城市 */
    @Excel(name = "所在城市")
    private String city;

    /** 所用的语言 en 英文 - zh_CN 简体中文 -zh_TW 繁体中文 */
    @Excel(name = "所用的语言 en 英文 - zh_CN 简体中文 -zh_TW 繁体中文")
    private String language;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nikeName;

    /** 头像地址 */
    @Excel(name = "头像地址")
    private String avatarUrl;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 性别 0：未知、1：男、2：女 */
    @Excel(name = "性别 0：未知、1：男、2：女")
    private Integer gender;

    /** 最近一次登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近一次登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLoginTime;

    /** 最近一次登录IP地址 */
    @Excel(name = "最近一次登录IP地址")
    private String lastLoginIp;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setMiniOpenId(String miniOpenId)
    {
        this.miniOpenId = miniOpenId;
    }

    public String getMiniOpenId()
    {
        return miniOpenId;
    }
    public void setAppOpenId(String appOpenId)
    {
        this.appOpenId = appOpenId;
    }

    public String getAppOpenId()
    {
        return appOpenId;
    }
    public void setUnionId(String unionId)
    {
        this.unionId = unionId;
    }

    public String getUnionId()
    {
        return unionId;
    }
    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCountry()
    {
        return country;
    }
    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getProvince()
    {
        return province;
    }
    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return city;
    }
    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getLanguage()
    {
        return language;
    }
    public void setNikeName(String nikeName)
    {
        this.nikeName = nikeName;
    }

    public String getNikeName()
    {
        return nikeName;
    }
    public void setAvatarUrl(String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl()
    {
        return avatarUrl;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setGender(Integer gender)
    {
        this.gender = gender;
    }

    public Integer getGender()
    {
        return gender;
    }
    public void setLastLoginTime(Date lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLoginTime()
    {
        return lastLoginTime;
    }
    public void setLastLoginIp(String lastLoginIp)
    {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLastLoginIp()
    {
        return lastLoginIp;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("miniOpenId", getMiniOpenId())
                .append("appOpenId", getAppOpenId())
                .append("unionId", getUnionId())
                .append("country", getCountry())
                .append("province", getProvince())
                .append("city", getCity())
                .append("language", getLanguage())
                .append("nikeName", getNikeName())
                .append("avatarUrl", getAvatarUrl())
                .append("phone", getPhone())
                .append("email", getEmail())
                .append("gender", getGender())
                .append("lastLoginTime", getLastLoginTime())
                .append("lastLoginIp", getLastLoginIp())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
