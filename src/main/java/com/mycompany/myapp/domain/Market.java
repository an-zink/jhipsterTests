package com.mycompany.myapp.domain;



import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.strapi.masterdata.model.StrapiContentType;

import io.swagger.annotations.ApiModelProperty;


/**
 * Market
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-06-30T16:41:29.588731200+02:00[Europe/Berlin]")


public class Market extends StrapiContentType implements Serializable  {
  private static final long serialVersionUID = 1L;

  @Id
  @JsonProperty("id")
  private String id;
 
  @JsonProperty("Key")
  private Integer key;

  @JsonProperty("Name")
  private String name;

  @JsonProperty("IsoCode")
  private String isoCode;

  @JsonProperty("published_at")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime publishedAt;
  
  
public Market() {
	
}
  
  
public Market id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Market key(Integer key) {
    this.key = key;
    return this;
  }

  /**
   * Get key
   * @return key
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  public Market name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Market isoCode(String isoCode) {
    this.isoCode = isoCode;
    return this;
  }

  /**
   * Get isoCode
   * @return isoCode
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(min=2,max=2) 
  public String getIsoCode() {
    return isoCode;
  }

  public void setIsoCode(String isoCode) {
    this.isoCode = isoCode;
  }

  public Market publishedAt(OffsetDateTime publishedAt) {
    this.publishedAt = publishedAt;
    return this;
  }

  /**
   * Get publishedAt
   * @return publishedAt
  */
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getPublishedAt() {
    return publishedAt;
  }

  public void setPublishedAt(OffsetDateTime publishedAt) {
    this.publishedAt = publishedAt;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Market market = (Market) o;
    return Objects.equals(this.id, market.id) &&
        Objects.equals(this.key, market.key) &&
        Objects.equals(this.name, market.name) &&
        Objects.equals(this.isoCode, market.isoCode) &&
        Objects.equals(this.publishedAt, market.publishedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, key, name, isoCode, publishedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Market {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    isoCode: ").append(toIndentedString(isoCode)).append("\n");
    sb.append("    publishedAt: ").append(toIndentedString(publishedAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

