package com.mycompany.myapp.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.strapi.masterdata.model.StrapiContentType;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * A Tisches.
 */
@Document(collection = "tisches")
public class Tisches extends StrapiContentType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @JsonProperty("Name")
    @Field("name")
    private String name;

    @JsonProperty("TischPlatte")
    @Field("TischPlatte")
    private String tischPlatte;
    
    @JsonProperty("TischBein")
    @Field("TischBein")
    private String tischBein;

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

    public Tisches name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTischPlatte() {
        return tischPlatte;
    }

    public Tisches tischPlatte(String tischPlatte) {
        this.tischPlatte = tischPlatte;
        return this;
    }

    public void setTischPlatte(String tischPlatte) {
        this.tischPlatte = tischPlatte;
    }

    public String getTischBein() {
        return tischBein;
    }

    public Tisches tischBein(String tischBein) {
        this.tischBein = tischBein;
        return this;
    }

    public void setTischBein(String tischBein) {
        this.tischBein = tischBein;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tisches)) {
            return false;
        }
        return id != null && id.equals(((Tisches) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Tisches{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", tischPlatte='" + getTischPlatte() + "'" +
            ", tischBein='" + getTischBein() + "'" +
            "}";
    }
}
