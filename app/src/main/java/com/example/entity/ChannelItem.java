package com.example.entity;

import java.io.Serializable;

/**
 * Created by admin on 2016/9/26.
 */
public class ChannelItem implements Serializable {
    private static final long serialVersionUID = -6465237897027410019L;

    private Integer id;
    private String name;
    private Integer OrderId;
    private Integer Selected;

    public ChannelItem() {
    }

    public ChannelItem(Integer id, String name, Integer orderId, Integer selected) {
        this.id = id;
        this.name = name;
        OrderId = orderId;
        Selected = selected;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Integer getOrderId() {
        return OrderId;
    }

    public void setOrderId(Integer orderId) {
        OrderId = orderId;
    }

    public Integer getSelected() {
        return Selected;
    }

    public void setSelected(Integer selected) {
        Selected = selected;
    }
}
