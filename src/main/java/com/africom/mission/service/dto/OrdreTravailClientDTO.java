package com.africom.mission.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.africom.mission.domain.OrdreTravailClient} entity.
 */
public class OrdreTravailClientDTO implements Serializable {
    
    private Long id;

    private String demandeur;

    private String origine;

    @NotNull
    private String motif;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrdreTravailClientDTO)) {
            return false;
        }

        return id != null && id.equals(((OrdreTravailClientDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrdreTravailClientDTO{" +
            "id=" + getId() +
            ", demandeur='" + getDemandeur() + "'" +
            ", origine='" + getOrigine() + "'" +
            ", motif='" + getMotif() + "'" +
            "}";
    }
}
