package com.africom.mission.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * A WorkOrder.
 */
@Entity
@Table(name = "work_order")
public class WorkOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "demandeur")
    private String demandeur;

    @NotNull
    @Column(name = "motif", nullable = false)
    private String motif;

    @NotNull
    @Column(name = "date_heure_debut_previsionnel", nullable = false)
    private ZonedDateTime dateHeureDebutPrevisionnel;

    @NotNull
    @Column(name = "date_heure_fin_previsionnel", nullable = false)
    private ZonedDateTime dateHeureFinPrevisionnel;

    @Column(name = "date_heure_debut_reel")
    private ZonedDateTime dateHeureDebutReel;

    @Column(name = "date_heure_fin_reel")
    private ZonedDateTime dateHeureFinReel;

    @Column(name = "materiel_utilise")
    private String materielUtilise;

    @Column(name = "remarque")
    private String remarque;

    @Column(name = "numero_fiche_intervention")
    private String numeroFicheIntervention;

    @Column(name = "hebergement", precision = 21, scale = 2)
    private BigDecimal hebergement;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDemandeur() {
        return demandeur;
    }

    public WorkOrder demandeur(String demandeur) {
        this.demandeur = demandeur;
        return this;
    }

    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    public String getMotif() {
        return motif;
    }

    public WorkOrder motif(String motif) {
        this.motif = motif;
        return this;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public ZonedDateTime getDateHeureDebutPrevisionnel() {
        return dateHeureDebutPrevisionnel;
    }

    public WorkOrder dateHeureDebutPrevisionnel(ZonedDateTime dateHeureDebutPrevisionnel) {
        this.dateHeureDebutPrevisionnel = dateHeureDebutPrevisionnel;
        return this;
    }

    public void setDateHeureDebutPrevisionnel(ZonedDateTime dateHeureDebutPrevisionnel) {
        this.dateHeureDebutPrevisionnel = dateHeureDebutPrevisionnel;
    }

    public ZonedDateTime getDateHeureFinPrevisionnel() {
        return dateHeureFinPrevisionnel;
    }

    public WorkOrder dateHeureFinPrevisionnel(ZonedDateTime dateHeureFinPrevisionnel) {
        this.dateHeureFinPrevisionnel = dateHeureFinPrevisionnel;
        return this;
    }

    public void setDateHeureFinPrevisionnel(ZonedDateTime dateHeureFinPrevisionnel) {
        this.dateHeureFinPrevisionnel = dateHeureFinPrevisionnel;
    }

    public ZonedDateTime getDateHeureDebutReel() {
        return dateHeureDebutReel;
    }

    public WorkOrder dateHeureDebutReel(ZonedDateTime dateHeureDebutReel) {
        this.dateHeureDebutReel = dateHeureDebutReel;
        return this;
    }

    public void setDateHeureDebutReel(ZonedDateTime dateHeureDebutReel) {
        this.dateHeureDebutReel = dateHeureDebutReel;
    }

    public ZonedDateTime getDateHeureFinReel() {
        return dateHeureFinReel;
    }

    public WorkOrder dateHeureFinReel(ZonedDateTime dateHeureFinReel) {
        this.dateHeureFinReel = dateHeureFinReel;
        return this;
    }

    public void setDateHeureFinReel(ZonedDateTime dateHeureFinReel) {
        this.dateHeureFinReel = dateHeureFinReel;
    }

    public String getMaterielUtilise() {
        return materielUtilise;
    }

    public WorkOrder materielUtilise(String materielUtilise) {
        this.materielUtilise = materielUtilise;
        return this;
    }

    public void setMaterielUtilise(String materielUtilise) {
        this.materielUtilise = materielUtilise;
    }

    public String getRemarque() {
        return remarque;
    }

    public WorkOrder remarque(String remarque) {
        this.remarque = remarque;
        return this;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getNumeroFicheIntervention() {
        return numeroFicheIntervention;
    }

    public WorkOrder numeroFicheIntervention(String numeroFicheIntervention) {
        this.numeroFicheIntervention = numeroFicheIntervention;
        return this;
    }

    public void setNumeroFicheIntervention(String numeroFicheIntervention) {
        this.numeroFicheIntervention = numeroFicheIntervention;
    }

    public BigDecimal getHebergement() {
        return hebergement;
    }

    public WorkOrder hebergement(BigDecimal hebergement) {
        this.hebergement = hebergement;
        return this;
    }

    public void setHebergement(BigDecimal hebergement) {
        this.hebergement = hebergement;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkOrder)) {
            return false;
        }
        return id != null && id.equals(((WorkOrder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkOrder{" +
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
