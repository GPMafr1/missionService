package com.africom.mission.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.africom.mission.domain.ArticleMission} entity.
 */
public class ArticleMissionDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer quantitePlanifiee;

    @NotNull
    private Integer quantiteUtilisee;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantitePlanifiee() {
        return quantitePlanifiee;
    }

    public void setQuantitePlanifiee(Integer quantitePlanifiee) {
        this.quantitePlanifiee = quantitePlanifiee;
    }

    public Integer getQuantiteUtilisee() {
        return quantiteUtilisee;
    }

    public void setQuantiteUtilisee(Integer quantiteUtilisee) {
        this.quantiteUtilisee = quantiteUtilisee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArticleMissionDTO)) {
            return false;
        }

        return id != null && id.equals(((ArticleMissionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArticleMissionDTO{" +
            "id=" + getId() +
            ", quantitePlanifiee=" + getQuantitePlanifiee() +
            ", quantiteUtilisee=" + getQuantiteUtilisee() +
            "}";
    }
}
