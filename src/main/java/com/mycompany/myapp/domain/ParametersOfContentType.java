package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ParametersOfContentType.
 */
@Document(collection = "parameters_of_content_type")
public class ParametersOfContentType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @DBRef
    @Field("contentTypeInfos")
    @JsonIgnore
    private Set<ContentTypeInfo> contentTypeInfos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<ContentTypeInfo> getContentTypeInfos() {
        return contentTypeInfos;
    }

    public ParametersOfContentType contentTypeInfos(Set<ContentTypeInfo> contentTypeInfos) {
        this.contentTypeInfos = contentTypeInfos;
        return this;
    }

    public ParametersOfContentType addContentTypeInfo(ContentTypeInfo contentTypeInfo) {
        this.contentTypeInfos.add(contentTypeInfo);
       // contentTypeInfo.getComponents().add(this);
        return this;
    }

    public ParametersOfContentType removeContentTypeInfo(ContentTypeInfo contentTypeInfo) {
        this.contentTypeInfos.remove(contentTypeInfo);
        //contentTypeInfo.getComponents().remove(this);
        return this;
    }

    public void setContentTypeInfos(Set<ContentTypeInfo> contentTypeInfos) {
        this.contentTypeInfos = contentTypeInfos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParametersOfContentType)) {
            return false;
        }
        return id != null && id.equals(((ParametersOfContentType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ParametersOfContentType{" +
            "id=" + getId() +
            "}";
    }
}
