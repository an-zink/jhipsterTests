package com.mycompany.myapp.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.strapi.masterdata.model.StrapiContentType;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * A Auto.
 */
@Document(collection = "auto")
public class Auto extends StrapiContentType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @JsonProperty("Name")
    @Field("name")
    private String name;

    @JsonProperty("Number")
    @Field("number")
    private Integer number;

    @JsonProperty("Modell")
    @Field("modell")
    private String modell;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Auto name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public Auto number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getModell() {
        return modell;
    }

    public Auto modell(String modell) {
        this.modell = modell;
        return this;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Auto)) {
            return false;
        }
        return id != null && id.equals(((Auto) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Auto{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", number=" + getNumber() +
            ", modell='" + getModell() + "'" +
            "}";
    }
}
