package com.example.project1.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import javax.persistence.Column;

@Entity
@Table(name="planets")
public class Planet {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="name")
	private String name;
    @Column(name="ownerid")
	private int ownerId;
	
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
    @Override
    public String toString() {
        return "Planet [id=" + id + ", name=" + name + ", ownerId=" + ownerId + "]";
    }
}

// comment