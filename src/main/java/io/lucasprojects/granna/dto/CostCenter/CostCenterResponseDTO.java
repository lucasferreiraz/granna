package io.lucasprojects.granna.dto.CostCenter;

public class CostCenterResponseDTO {

    private Long id;
    private String description;
    private String observation;

    public CostCenterResponseDTO() { }

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
    
}
