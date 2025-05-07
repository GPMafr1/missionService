package com.africom.mission.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.africom.mission.domain.Tache} entity.
 */
public class TacheDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String roleMission;

    @NotNull
    private String note;

    @NotNull
    private BigDecimal remboursement;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleMission() {
        return roleMission;
    }

    public void setRoleMission(String roleMission) {
        this.roleMission = roleMission;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getRemboursement() {
        return remboursement;
    }

    public void setRemboursement(BigDecimal remboursement) {
        this.remboursement = remboursement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TacheDTO)) {
            return false;
        }

        return id != null && id.equals(((TacheDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TacheDTO{" +
            "id=" + getId() +
            ", roleMission='" + getRoleMission() + "'" +
            ", note='" + getNote() + "'" +
            ", remboursement=" + getRemboursement() +
            "}";
    }
}
