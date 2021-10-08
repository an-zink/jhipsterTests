package com.mycompany.myapp.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ContentTypeInfo.
 */
@Document(collection = "content_type_info")
public class ContentTypeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Size(min = 2)
    @Field("content_type_name")
    private String contentTypeName;

    @Size(min = 3, max = 3)
    @Field("enviroment")
    private String enviroment;

//    @Size(min = 2)
//    @Field("last_modified_by_strapi_user")
//    private String lastModifiedByStrapiUser;

    @Field("last_modified_date")
    private String lastModifiedDate;

    @Field("number_of_entries")
    private Integer numberOfEntries;

    @Field("number_of_parameters")
    private Integer numberOfParameters;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentTypeName() {
        return contentTypeName;
    }

    public ContentTypeInfo contentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
        return this;
    }

    public void setContentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
    }

    public String getEnviroment() {
        return enviroment;
    }

    public ContentTypeInfo enviroment(String enviroment) {
        this.enviroment = enviroment;
        return this;
    }

    public void setEnviroment(String enviroment) {
        this.enviroment = enviroment;
    }

//    public String getLastModifiedByStrapiUser() {
//        return lastModifiedByStrapiUser;
//    }
//
//    public ContentTypeInfo lastModifiedByStrapiUser(String lastModifiedByStrapiUser) {
//        this.lastModifiedByStrapiUser = lastModifiedByStrapiUser;
//        return this;
//    }
//
//    public void setLastModifiedByStrapiUser(String lastModifiedByStrapiUser) {
//        this.lastModifiedByStrapiUser = lastModifiedByStrapiUser;
//    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public ContentTypeInfo lastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getNumberOfEntries() {
        return numberOfEntries;
    }

    public ContentTypeInfo numberOfEntries(Integer numberOfEntries) {
        this.numberOfEntries = numberOfEntries;
        return this;
    }

    public void setNumberOfEntries(Integer numberOfEntries) {
        this.numberOfEntries = numberOfEntries;
    }

    public Integer getNumberOfParameters() {
        return numberOfParameters;
    }

    public ContentTypeInfo numberOfParameters(Integer numberOfParameters) {
        this.numberOfParameters = numberOfParameters;
        return this;
    }

    public void setNumberOfParameters(Integer numberOfParameters) {
        this.numberOfParameters = numberOfParameters;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContentTypeInfo)) {
            return false;
        }
        return id != null && id.equals(((ContentTypeInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ContentTypeInfo{" +
            "id=" + getId() +
            ", contentTypeName='" + getContentTypeName() + "'" +
            ", enviroment='" + getEnviroment() + "'" +
//            ", lastModifiedByStrapiUser='" + getLastModifiedByStrapiUser() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", numberOfEntries=" + getNumberOfEntries() +
            ", numberOfParameters=" + getNumberOfParameters() +
            "}";
    }
}
