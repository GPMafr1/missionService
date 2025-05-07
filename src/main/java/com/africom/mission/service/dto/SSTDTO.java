package com.africom.mission.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import com.africom.mission.domain.enumeration.ImportanceSST;

/**
 * A DTO for the {@link com.africom.mission.domain.SST} entity.
 */
public class SSTDTO implements Serializable {
    
    private Long id;

    private String label;

    private LocalDate date;

    private ImportanceSST importance;

    private String commentaire;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ImportanceSST getImportance() {
        return importance;
    }

    public void setImportance(ImportanceSST importance) {
        this.importance = importance;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SSTDTO)) {
            return false;
        }

        return id != null && id.equals(((SSTDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SSTDTO{" +
            "id=" + getId() +
            ", label='" + getLabel() + "'" +
            ", date='" + getDate() + "'" +
            ", importance='" + getImportance() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            "}";
    }
}
