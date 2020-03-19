package top.jjmaps.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * 需要自己构建自己的user对象
 */
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 464435031119310097L;
    //主键id
    private Integer id;
    //用户姓名
    private String name;
    //登录名
    private String username;
    //登录密码
    private String password;

    //其他信息
    private String notes;

    //数据状态 启用 禁用
    private Boolean status;


    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date ctime;
    //修改时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date utime;

    //权限集合


    //新增的详情字段
    private Integer roleId;
    private String roleName;
    private String phone;//用户电话
    private String code;//用户邀请码
    private String smsMsgId;//发送验证码时返还的id
    private String lbs;//用户的支付宝地域系数

    public String getLbs() {
        return lbs;
    }

    private Integer lbsMoney;//支付宝地域系数对应金额
    //验证码
    private String yzCode;

    private Integer tbLbsId;//淘宝地域系数对应金额

    private Integer tbLbsMoney;//淘宝地域系数对应金额
    private Integer tbReLbsMoney;//淘宝地域系数对应复购金额
    private String tbLbs;//用户的淘宝地域系数

    private Integer jdLbsMoney;//京东地域系数对应金额
    private String jdLbs;//用户的京东地域系数


    public String getTbLbs() {
        return tbLbs;
    }

    public void setTbLbs(String tbLbs) {
        this.tbLbs = tbLbs;
    }


    public Integer getTbLbsMoney() {
        return tbLbsMoney;
    }

    public void setTbLbsMoney(Integer tbLbsMoney) {
        this.tbLbsMoney = tbLbsMoney;
    }

    public String getYzCode() {
        return yzCode;
    }

    public void setYzCode(String yzCode) {
        this.yzCode = yzCode;
    }

    public Integer getLbsMoney() {
        return lbsMoney;
    }

    public void setLbsMoney(Integer lbsMoney) {
        this.lbsMoney = lbsMoney;
    }

    public void setLbs(String lbs) {
        this.lbs = lbs;
    }

    public String getSmsMsgId() {
        return smsMsgId;
    }

    public void setSmsMsgId(String smsMsgId) {
        this.smsMsgId = smsMsgId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }


    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getJdLbsMoney() {
        return jdLbsMoney;
    }

    public void setJdLbsMoney(Integer jdLbsMoney) {
        this.jdLbsMoney = jdLbsMoney;
    }

    public String getJdLbs() {
        return jdLbs;
    }

    public void setJdLbs(String jdLbs) {
        this.jdLbs = jdLbs;
    }

    public Integer getTbReLbsMoney() {
        return tbReLbsMoney;
    }

    public void setTbReLbsMoney(Integer tbReLbsMoney) {
        this.tbReLbsMoney = tbReLbsMoney;
    }
}
