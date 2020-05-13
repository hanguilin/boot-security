package com.spring.security.entity.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 用户表(SysUser)实体类
 *
 * @author makejava
 * @since 2019-09-03 15:06:48
 */
@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable {
	
    private static final long serialVersionUID = 915478504870211231L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    //账号
    @Column(name = "account")
    private String account;
    
    //用户名
    @Column(name = "user_name")
    private String userName;
    
    //用户密码
    @JsonIgnore
    @Column(name = "password")
    private String password;
    
    // 头像
    private String avatar;
    
    // 个人签名
    private String signature;
    
    //上一次登录时间
    @Column(name = "last_login_time")
    private String lastLoginTime;
    
    //账号是否可用。默认为1（可用）
    @JsonIgnore
    @Column(name = "enabled")
    private Boolean enabled;
    
    //是否过期。默认为1（没有过期）
    @JsonIgnore
    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;
    
    //账号是否锁定。默认为1（没有锁定）
    @JsonIgnore
    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;
    
    //证书（密码）是否过期。默认为1（没有过期）
    @JsonIgnore
    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;
    
    //创建时间
    @JsonIgnore
    @Column(name = "create_time")
    private String createTime;
    
    //修改时间
    @JsonIgnore
    @Column(name = "update_time")
    private String updateTime;
    
    //创建人
    @JsonIgnore
    @Column(name = "create_user")
    private Integer createUser;
    
    //修改人
    @JsonIgnore
    @Column(name = "update_user")
    private Integer updateUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
    
}