package com.africom.mission.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.africom.mission.domain.enumeration.ImportanceSST;

/**
 * A SST.
 */
@Entity
@Table(name = "sst")
public class SST implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "label")
    private String label;

    @Column(name = "date")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "importance")
    private ImportanceSST importance;

    @Column(name = "commentaire")
    private String commentaire;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public SST label(String label) {
        this.label = label;
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LocalDate getDate() {
        return date;
    }

    public SST date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ImportanceSST getImportance() {
        return importance;
    }

    public SST importance(ImportanceSST importance) {
        this.importance = importance;
        return this;
    }

    public void setImportance(ImportanceSST importance) {
        this.importance = importance;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public SST commentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SST)) {
            return false;
        }
        return id != null && id.equals(((SST) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SST{" +
            "id=" + getId() +
            ", label='" + getLabel() + "'" +
            ", date='" + getDate() + "'" +
            ", importance='" + getImportance() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            "}";
    }
}
