package com.africom.mission.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A OrdreTravailClient.
 */
@Entity
@Table(name = "ordre_travail_client")
public class OrdreTravailClient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "demandeur")
    private String demandeur;

    @Column(name = "origine")
    private String origine;

    @NotNull
    @Column(name = "motif", nullable = false)
    private String motif;

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

    public OrdreTravailClient demandeur(String demandeur) {
        this.demandeur = demandeur;
        return this;
    }

    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    public String getOrigine() {
        return origine;
    }

    public OrdreTravailClient origine(String origine) {
        this.origine = origine;
        return this;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getMotif() {
        return motif;
    }

    public OrdreTravailClient motif(String motif) {
        this.motif = motif;
        return this;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrdreTravailClient)) {
            return false;
        }
        return id != null && id.equals(((OrdreTravailClient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrdreTravailClient{" +
            "id=" + getId() +
            ", demandeur='" + getDemandeur() + "'" +
            ", origine='" + getOrigine() + "'" +
            ", motif='" + getMotif() + "'" +
            "}";
    }
}
