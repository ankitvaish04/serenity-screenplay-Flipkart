
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
@JsonPropertyOrder({
    "testCases"
})
public class PrintRequest implements Serializable
{

    @JsonProperty("testCases")
    private List<TestCase> testCases = new ArrayList<TestCase>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -2947340710604670049L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PrintRequest() {
    }

    /**
     * 
     * @param testCases
     */
    public PrintRequest(List<TestCase> testCases) {
        super();
        this.testCases = testCases;
    }

    @JsonProperty("testCases")
    public List<TestCase> getTestCases() {
        return testCases;
    }

    @JsonProperty("testCases")
    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public PrintRequest withTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
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

    public PrintRequest withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("testCases", testCases).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(testCases).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PrintRequest) == false) {
            return false;
        }
        PrintRequest rhs = ((PrintRequest) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(testCases, rhs.testCases).isEquals();
    }

}
