package com.cekj.didimain.db.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table CALL_ENTITY.
 */
public class CallEntity {

    private Long id;
    private int orderId;
    /** Not-null value. */
    private String orderType;
    /** Not-null value. */
    private String orderTime;
    /** Not-null value. */
    private String description;

    public CallEntity() {
    }

    public CallEntity(Long id) {
        this.id = id;
    }

    public CallEntity(Long id, int orderId, String orderType, String orderTime, String description) {
        this.id = id;
        this.orderId = orderId;
        this.orderType = orderType;
        this.orderTime = orderTime;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /** Not-null value. */
    public String getOrderType() {
        return orderType;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /** Not-null value. */
    public String getOrderTime() {
        return orderTime;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    /** Not-null value. */
    public String getDescription() {
        return description;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setDescription(String description) {
        this.description = description;
    }

}
