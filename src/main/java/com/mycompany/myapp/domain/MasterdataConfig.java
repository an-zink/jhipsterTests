package com.mycompany.myapp.domain;
import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.test.strapi.masterdata.model.StrapiContentType;

/**
 * A MasterdataConfig.
 */
@Document(collection = "masterdataConfig")
public class MasterdataConfig extends StrapiContentType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    
    @Field("name")
    private String name;

    @Field("path")
    private String path;

    @Field("clazz")
    private String clazz;

    @Field("collection_name")
    private String collectionName;

    @Field("environment")
    private String environment;

    @Field("content_type")
    private String contentType;

    @Field("port")
    private String port;

    @Field("url")
    private String url;

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

    public MasterdataConfig name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public MasterdataConfig path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getClazz() {
        return clazz;
    }

    public MasterdataConfig clazz(String clazz) {
        this.clazz = clazz;
        return this;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public MasterdataConfig collectionName(String collectionName) {
        this.collectionName = collectionName;
        return this;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getEnvironment() {
        return environment;
    }

    public MasterdataConfig environment(String environment) {
        this.environment = environment;
        return this;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getContentType() {
        return contentType;
    }

    public MasterdataConfig contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPort() {
        return port;
    }

    public MasterdataConfig port(String port) {
        this.port = port;
        return this;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public MasterdataConfig url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MasterdataConfig)) {
            return false;
        }
        return id != null && id.equals(((MasterdataConfig) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MasterdataConfig{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", path='" + getPath() + "'" +
            ", clazz='" + getClazz() + "'" +
            ", collectionName='" + getCollectionName() + "'" +
            ", environment='" + getEnvironment() + "'" +
            ", contentType='" + getContentType() + "'" +
            ", port='" + getPort() + "'" +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
