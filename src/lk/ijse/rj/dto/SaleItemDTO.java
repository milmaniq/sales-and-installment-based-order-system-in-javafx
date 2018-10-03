/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.dto;

import java.math.BigDecimal;

/**
 *
 * @author Ilman Iqbal
 */
public class SaleItemDTO {
    private String itemId;
    private String description;
    private BigDecimal price;

    public SaleItemDTO() {
    }

    public SaleItemDTO(String itemId, String description, BigDecimal price) {
        this.itemId = itemId;
        this.description = description;
        this.price = price;
    }

    
    /**
     * @return the itemId
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

   
}
