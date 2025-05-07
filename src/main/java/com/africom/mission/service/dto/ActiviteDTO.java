package com.africom.mission.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.africom.mission.domain.enumeration.TypeActivite;

/**
 * A DTO for the {@link com.africom.mission.domain.Activite} entity.
 */
public class ActiviteDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String designation;

    @NotNull
    private TypeActivite type;

    
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public TypeActivite getType() {
        return type;
    }

    public void setType(TypeActivite type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActiviteDTO)) {
            return false;
        }

        return id != null && id.equals(((ActiviteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActiviteDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
