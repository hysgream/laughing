package com.laughing2b.html.dal;

import java.util.Date;

public class LaughingUser {
    private Integer id;

    private String nickname;

    private String email;

    private String password;

    private String phone;

    private String loginIp;

    private String icon;

    private String des;

    private String lifeMember;

    private String yearMember;

    private String monthMember;

    private Integer assets;

    private String status;

    private String exta;

    private String extb;

    private String extc;

    private Date effectiveStartTime=new Date();

    private Date effectiveEndTime=new Date();

    private Date createTime=new Date();

    private Date updateTime=new Date();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public String getLifeMember() {
        return lifeMember;
    }

    public void setLifeMember(String lifeMember) {
        this.lifeMember = lifeMember == null ? null : lifeMember.trim();
    }

    public String getYearMember() {
        return yearMember;
    }

    public void setYearMember(String yearMember) {
        this.yearMember = yearMember == null ? null : yearMember.trim();
    }

    public String getMonthMember() {
        return monthMember;
    }

    public void setMonthMember(String monthMember) {
        this.monthMember = monthMember == null ? null : monthMember.trim();
    }

    public Integer getAssets() {
        return assets;
    }

    public void setAssets(Integer assets) {
        this.assets = assets;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getExta() {
        return exta;
    }

    public void setExta(String exta) {
        this.exta = exta == null ? null : exta.trim();
    }

    public String getExtb() {
        return extb;
    }

    public void setExtb(String extb) {
        this.extb = extb == null ? null : extb.trim();
    }

    public String getExtc() {
        return extc;
    }

    public void setExtc(String extc) {
        this.extc = extc == null ? null : extc.trim();
    }

    public Date getEffectiveStartTime() {
        return effectiveStartTime;
    }

    public void setEffectiveStartTime(Date effectiveStartTime) {
        this.effectiveStartTime = effectiveStartTime;
    }

    public Date getEffectiveEndTime() {
        return effectiveEndTime;
    }

    public void setEffectiveEndTime(Date effectiveEndTime) {
        this.effectiveEndTime = effectiveEndTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}