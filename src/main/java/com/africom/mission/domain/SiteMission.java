package com.africom.mission.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A SiteMission.
 */
@Entity
@Table(name = "site_mission")
public class SiteMission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "commentaire")
    private String commentaire;

    @NotNull
    @Column(name = "date_heure_debut_reel", nullable = false)
    private ZonedDateTime dateHeureDebutReel;

    @NotNull
    @Column(name = "date_heure_fin_reel", nullable = false)
    private ZonedDateTime dateHeureFinReel;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public SiteMission code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public SiteMission commentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public ZonedDateTime getDateHeureDebutReel() {
        return dateHeureDebutReel;
    }

    public SiteMission dateHeureDebutReel(ZonedDateTime dateHeureDebutReel) {
        this.dateHeureDebutReel = dateHeureDebutReel;
        return this;
    }

    public void setDateHeureDebutReel(ZonedDateTime dateHeureDebutReel) {
        this.dateHeureDebutReel = dateHeureDebutReel;
    }

    public ZonedDateTime getDateHeureFinReel() {
        return dateHeureFinReel;
    }

    public SiteMission dateHeureFinReel(ZonedDateTime dateHeureFinReel) {
        this.dateHeureFinReel = dateHeureFinReel;
        return this;
    }

    public void setDateHeureFinReel(ZonedDateTime dateHeureFinReel) {
        this.dateHeureFinReel = dateHeureFinReel;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SiteMission)) {
            return false;
        }
        return id != null && id.equals(((SiteMission) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SiteMission{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            ", dateHeureDebutReel='" + getDateHeureDebutReel() + "'" +
            ", dateHeureFinReel='" + getDateHeureFinReel() + "'" +
            "}";
    }
}
