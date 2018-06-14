
package com.rsystem.uniprint.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "file", "profile", "properties" })
public class TestCase implements Serializable {

	@JsonProperty("file")
	private String file;
	
	@JsonProperty("profile")
	private String profile;

	@JsonProperty("properties")
	private List<Property> properties = new ArrayList<Property>();

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	private final static long serialVersionUID = 3172765772806233251L;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public TestCase() {
	}

	/**
	 * 
	 * @param file
	 * @param properties
	 * @param profile
	 */
	public TestCase(String file, String profile, List<Property> properties) {
		super();
		this.file = file;
		this.profile = profile;
		this.properties = properties;
	}

	@JsonProperty("file")
	public String getFile() {
		return file;
	}

	@JsonProperty("file")
	public void setFile(String file) {
		this.file = file;
	}

	public TestCase withFile(String file) {
		this.file = file;
		return this;
	}

	@JsonProperty("profile")
	public String getProfile() {
		return profile;
	}

	@JsonProperty("profile")
	public void setProfile(String profile) {
		this.profile = profile;
	}

	public TestCase withProfile(String profile) {
		this.profile = profile;
		return this;
	}

	@JsonProperty("properties")
	public List<Property> getProperties() {
		return properties;
	}

	@JsonProperty("properties")
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public TestCase withProperties(List<Property> properties) {
		this.properties = properties;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public TestCase withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("file", file).append("profile", profile)
				.append("properties", properties).append("additionalProperties", additionalProperties).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(additionalProperties).append(file).append(properties).append(profile)
				.toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof TestCase) == false) {
			return false;
		}
		TestCase rhs = ((TestCase) other);
		return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(file, rhs.file)
				.append(properties, rhs.properties).append(profile, rhs.profile).isEquals();
	}

}
