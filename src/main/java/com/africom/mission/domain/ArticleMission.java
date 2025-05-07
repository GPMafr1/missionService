package com.africom.mission.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ArticleMission.
 */
@Entity
@Table(name = "article_mission")
public class ArticleMission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "quantite_planifiee", nullable = false)
    private Integer quantitePlanifiee;

    @NotNull
    @Column(name = "quantite_utilisee", nullable = false)
    private Integer quantiteUtilisee;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantitePlanifiee() {
        return quantitePlanifiee;
    }

    public ArticleMission quantitePlanifiee(Integer quantitePlanifiee) {
        this.quantitePlanifiee = quantitePlanifiee;
        return this;
    }

    public void setQuantitePlanifiee(Integer quantitePlanifiee) {
        this.quantitePlanifiee = quantitePlanifiee;
    }

    public Integer getQuantiteUtilisee() {
        return quantiteUtilisee;
    }

    public ArticleMission quantiteUtilisee(Integer quantiteUtilisee) {
        this.quantiteUtilisee = quantiteUtilisee;
        return this;
    }

    public void setQuantiteUtilisee(Integer quantiteUtilisee) {
        this.quantiteUtilisee = quantiteUtilisee;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArticleMission)) {
            return false;
        }
        return id != null && id.equals(((ArticleMission) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArticleMission{" +
            "id=" + getId() +
            ", quantitePlanifiee=" + getQuantitePlanifiee() +
            ", quantiteUtilisee=" + getQuantiteUtilisee() +
            "}";
    }
}
