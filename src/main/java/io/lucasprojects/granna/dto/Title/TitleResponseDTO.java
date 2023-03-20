package io.lucasprojects.granna.dto.Title;

import java.util.Date;
import java.util.List;

import io.lucasprojects.granna.domain.Enum.TitleType;
import io.lucasprojects.granna.dto.CostCenter.CostCenterResponseDTO;

public class TitleResponseDTO {
    private Long id;
    private String description;
    private String observation;
    private Double value;
    private List<CostCenterResponseDTO> costCenters;
    private TitleType titleType;
    private Date registerDate;
    private Date referenceDate;
    private Date dueDate;
    private Date payDay;

    public TitleResponseDTO() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public List<CostCenterResponseDTO> getCostCenters() {
        return costCenters;
    }

    public void setCostCenters(List<CostCenterResponseDTO> costCenters) {
        this.costCenters = costCenters;
    }

    public TitleType getTitleType() {
        return titleType;
    }

    public void setTitleType(TitleType titleType) {
        this.titleType = titleType;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getReferenceDate() {
        return referenceDate;
    }

    public void setReferenceDate(Date referenceDate) {
        this.referenceDate = referenceDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPayDay() {
        return payDay;
    }

    public void setPayDay(Date payDay) {
        this.payDay = payDay;
    }
 
}
