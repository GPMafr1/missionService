package com.africom.mission.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.africom.mission.domain.SiteMission} entity.
 */
public class SiteMissionDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String code;

    private String commentaire;

    @NotNull
    private ZonedDateTime dateHeureDebutReel;

    @NotNull
    private ZonedDateTime dateHeureFinReel;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SiteMissionDTO)) {
            return false;
        }

        return id != null && id.equals(((SiteMissionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SiteMissionDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            ", dateHeureDebutReel='" + getDateHeureDebutReel() + "'" +
            ", dateHeureFinReel='" + getDateHeureFinReel() + "'" +
            "}";
    }
}
