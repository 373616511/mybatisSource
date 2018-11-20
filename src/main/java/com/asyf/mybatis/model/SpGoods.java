package com.asyf.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

public class SpGoods {
    private String id;

    private String goodsName;

    private Integer sort;

    private String shoppingPointsCategoryId;

    private BigDecimal goodsPrice;

    private Date periodValidityBegin;

    private Date periodValidityEnd;

    private String goodsImage;

    private Integer stock;

    private Integer stockLess;

    private Integer stockWarn;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer delFlag;

    private String goodsDetail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getShoppingPointsCategoryId() {
        return shoppingPointsCategoryId;
    }

    public void setShoppingPointsCategoryId(String shoppingPointsCategoryId) {
        this.shoppingPointsCategoryId = shoppingPointsCategoryId == null ? null : shoppingPointsCategoryId.trim();
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Date getPeriodValidityBegin() {
        return periodValidityBegin;
    }

    public void setPeriodValidityBegin(Date periodValidityBegin) {
        this.periodValidityBegin = periodValidityBegin;
    }

    public Date getPeriodValidityEnd() {
        return periodValidityEnd;
    }

    public void setPeriodValidityEnd(Date periodValidityEnd) {
        this.periodValidityEnd = periodValidityEnd;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage == null ? null : goodsImage.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStockLess() {
        return stockLess;
    }

    public void setStockLess(Integer stockLess) {
        this.stockLess = stockLess;
    }

    public Integer getStockWarn() {
        return stockWarn;
    }

    public void setStockWarn(Integer stockWarn) {
        this.stockWarn = stockWarn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail == null ? null : goodsDetail.trim();
    }
}