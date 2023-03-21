package io.lucasprojects.granna.dto.DashBoard;

import java.util.List;

import io.lucasprojects.granna.dto.Title.TitleResponseDTO;

public class DashBoardResponseDTO {
    
    private Double totalToPay;
    private Double totalToReceive;
    private Double balance;

    private List<TitleResponseDTO> titlesToPay;
    private List<TitleResponseDTO> titlesToReceive;

    public DashBoardResponseDTO() { }

    public DashBoardResponseDTO(Double totalToPay, Double totalToReceive, Double balance,
            List<TitleResponseDTO> titlesToPay, List<TitleResponseDTO> titlesToReceive) {
        this.totalToPay = totalToPay;
        this.totalToReceive = totalToReceive;
        this.balance = balance;
        this.titlesToPay = titlesToPay;
        this.titlesToReceive = titlesToReceive;
    }

    public Double getTotalToPay() {
        return totalToPay;
    }

    public void setTotalToPay(Double totalToPay) {
        this.totalToPay = totalToPay;
    }

    public Double getTotalToReceive() {
        return totalToReceive;
    }

    public void setTotalToReceive(Double totalToReceive) {
        this.totalToReceive = totalToReceive;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<TitleResponseDTO> getTitlesToPay() {
        return titlesToPay;
    }

    public void setTitlesToPay(List<TitleResponseDTO> titlesToPay) {
        this.titlesToPay = titlesToPay;
    }

    public List<TitleResponseDTO> getTitlesToReceive() {
        return titlesToReceive;
    }

    public void setTitlesToReceive(List<TitleResponseDTO> titlesToReceive) {
        this.titlesToReceive = titlesToReceive;
    }
    
}
