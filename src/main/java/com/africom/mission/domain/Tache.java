package com.africom.mission.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Tache.
 */
@Entity
@Table(name = "tache")
public class Tache implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "role_mission", nullable = false)
    private String roleMission;

    @NotNull
    @Column(name = "note", nullable = false)
    private String note;

    @NotNull
    @Column(name = "remboursement", precision = 21, scale = 2, nullable = false)
    private BigDecimal remboursement;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleMission() {
        return roleMission;
    }

    public Tache roleMission(String roleMission) {
        this.roleMission = roleMission;
        return this;
    }

    public void setRoleMission(String roleMission) {
        this.roleMission = roleMission;
    }

    public String getNote() {
        return note;
    }

    public Tache note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getRemboursement() {
        return remboursement;
    }

    public Tache remboursement(BigDecimal remboursement) {
        this.remboursement = remboursement;
        return this;
    }

    public void setRemboursement(BigDecimal remboursement) {
        this.remboursement = remboursement;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tache)) {
            return false;
        }
        return id != null && id.equals(((Tache) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tache{" +
            "id=" + getId() +
            ", roleMission='" + getRoleMission() + "'" +
            ", note='" + getNote() + "'" +
            ", remboursement=" + getRemboursement() +
            "}";
    }
}
