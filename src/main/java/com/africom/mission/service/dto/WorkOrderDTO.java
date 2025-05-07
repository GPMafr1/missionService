package com.africom.mission.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.africom.mission.domain.WorkOrder} entity.
 */
public class WorkOrderDTO implements Serializable {
    
    private Long id;

    private String demandeur;

    @NotNull
    private String motif;

    @NotNull
    private ZonedDateTime dateHeureDebutPrevisionnel;

    @NotNull
    private ZonedDateTime dateHeureFinPrevisionnel;

    private ZonedDateTime dateHeureDebutReel;

    private ZonedDateTime dateHeureFinReel;

    private String materielUtilise;

    private String remarque;

    private String numeroFicheIntervention;

    private BigDecimal hebergement;

    
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

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public ZonedDateTime getDateHeureDebutPrevisionnel() {
        return dateHeureDebutPrevisionnel;
    }

    public void setDateHeureDebutPrevisionnel(ZonedDateTime dateHeureDebutPrevisionnel) {
        this.dateHeureDebutPrevisionnel = dateHeureDebutPrevisionnel;
    }

    public ZonedDateTime getDateHeureFinPrevisionnel() {
        return dateHeureFinPrevisionnel;
    }

    public void setDateHeureFinPrevisionnel(ZonedDateTime dateHeureFinPrevisionnel) {
        this.dateHeureFinPrevisionnel = dateHeureFinPrevisionnel;
    }

    public ZonedDateTime getDateHeureDebutReel() {
        return dateHeureDebutReel;
    }

    public void setDateHeureDebutReel(ZonedDateTime dateHeureDebutReel) {
        this.dateHeureDebutReel = dateHeureDebutReel;
    }

    public ZonedDateTime getDateHeureFinReel() {
        return dateHeureFinReel;
    }

    public void setDateHeureFinReel(ZonedDateTime dateHeureFinReel) {
        this.dateHeureFinReel = dateHeureFinReel;
    }

    public String getMaterielUtilise() {
        return materielUtilise;
    }

    public void setMaterielUtilise(String materielUtilise) {
        this.materielUtilise = materielUtilise;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getNumeroFicheIntervention() {
        return numeroFicheIntervention;
    }

    public void setNumeroFicheIntervention(String numeroFicheIntervention) {
        this.numeroFicheIntervention = numeroFicheIntervention;
    }

    public BigDecimal getHebergement() {
        return hebergement;
    }

    public void setHebergement(BigDecimal hebergement) {
        this.hebergement = hebergement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkOrderDTO)) {
            return false;
        }

        return id != null && id.equals(((WorkOrderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkOrderDTO{" +
            "id=" + getId() +
            ", demandeur='" + getDemandeur() + "'" +
            ", motif='" + getMotif() + "'" +
            ", dateHeureDebutPrevisionnel='" + getDateHeureDebutPrevisionnel() + "'" +
            ", dateHeureFinPrevisionnel='" + getDateHeureFinPrevisionnel() + "'" +
            ", dateHeureDebutReel='" + getDateHeureDebutReel() + "'" +
            ", dateHeureFinReel='" + getDateHeureFinReel() + "'" +
            ", materielUtilise='" + getMaterielUtilise() + "'" +
            ", remarque='" + getRemarque() + "'" +
            ", numeroFicheIntervention='" + getNumeroFicheIntervention() + "'" +
            ", hebergement=" + getHebergement() +
            "}";
    }
}
