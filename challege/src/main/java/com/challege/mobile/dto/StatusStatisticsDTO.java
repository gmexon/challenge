/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challege.mobile.dto;

/**
 *
 * @author Andrea
 */
public class StatusStatisticsDTO {
  private String status;
  private Long   cnt;

    public StatusStatisticsDTO(String status, Long cnt) {
        this.status = status;
        this.cnt = cnt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCnt() {
        return cnt;
    }

    public void setCnt(Long cnt) {
        this.cnt = cnt;
    }
  
}

