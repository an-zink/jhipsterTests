package com.mycompany.myapp.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.strapi.masterdata.model.StrapiContentType;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * A Haus.
 */
@Document(collection = "haus")
public class Haus extends StrapiContentType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @JsonProperty("Nr")
    @Field("nr")
    private String nr;

    @JsonProperty("Name")
    @Field("name")
    private String name;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNr() {
        return nr;
    }

    public Haus nr(String nr) {
        this.nr = nr;
        return this;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getName() {
        return name;
    }

    public Haus name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Haus)) {
            return false;
        }
        return id != null && id.equals(((Haus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Haus{" +
            "id=" + getId() +
            ", nr='" + getNr() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
