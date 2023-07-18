package com.szcgc.wechat.smart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: chenxinli
 * @Date: 2020/7/31 15:18
 * @Description:
 */

@Entity
@Table(name = "bookpurchase",schema = "wechatbusiness")
public class BookPurchase {

    @Id
    @Column(name = "id", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false,length = 50)
    private String bookname;

    @Column
    private int quantity;

    @Column
    private BigDecimal totalprice;

    @Column(length = 20)
    private String customname;

    @Column(length = 50)
    private String customcorp;

    @Column(length = 100)
    private String area;

    @Column(length = 200)
    private String address;

    @Column(length = 20)
    private String cellphone;

    @Column(length = 50)
    private String email;

    @Column
    private LocalDateTime createTime;

    @Column
    private byte push;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomname() {
        return customname;
    }

    public void setCustomname(String customname) {
        this.customname = customname;
    }

    public String getCustomcorp() {
        return customcorp;
    }

    public void setCustomcorp(String customcorp) {
        this.customcorp = customcorp;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public byte getPush() {
        return push;
    }

    public void setPush(byte push) {
        this.push = push;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    @PrePersist()
    public void onCreate(){
        this.createTime = LocalDateTime.now();
        this.push = 0;
    }
}
