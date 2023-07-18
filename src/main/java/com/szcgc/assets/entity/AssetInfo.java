package com.szcgc.assets.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author JINLINGXUAN
 * @create 2021-06-07
 * 固定资产表
 */

@Entity
@Table(name = "assetsinfo", schema = "wechatbusiness")
public class AssetInfo {

    @JsonIgnore
    @Id
    @Column(name = "id", length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, updatable = false)
    private String assetCode;//资产编码

    @Column(length = 30)
    private String assetName;//资产名称

    @Column(length = 30)
    private String brand;//品牌

    @Column(length = 50)
    private String productDate;//出厂日期

    @Column(length = 50)
    private String buyDate;//购置日期

    @Column(length = 30)
    private String user;//领用人

    @Column(length = 50)
    private String useDate;//领用日期

    @Column(length = 50)
    private String location;//存放地点

    @JsonIgnore
    @Column(updatable = false)
    private LocalDateTime createAt;

    @JsonIgnore
    @Column(updatable = false)
    private LocalDateTime updateAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @PrePersist
    public void onCreate() {
        createAt = LocalDateTime.now();
        updateAt = createAt;
    }

    @PreUpdate
    public void onUpdate() {
        updateAt = LocalDateTime.now();
    }
}
