package com.tleaf.demo.domain;

import javax.persistence.*;

/**
 * @author Allan Tejano
 * 4/23/2018
 */
@Entity
@Table(name = "Organizations")
public class Organization {

    public static Organization of( Integer organizationId ) {
        Organization organization = new Organization();
        organization.setOrganizationId( organizationId );
        return organization;
    }

    public Organization() {
    }

    public Organization( Integer organizationId, String name ) {
        this.organizationId = organizationId;
        this.name = name;
    }

    @Id
    @Column(name = "organization_id")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer organizationId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId( Integer organizationId ) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }
}
