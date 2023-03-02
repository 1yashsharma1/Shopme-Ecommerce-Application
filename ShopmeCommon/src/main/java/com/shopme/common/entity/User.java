package com.shopme.common.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Integer id;

    @Column(length = 128, unique = true, nullable = false)
    @ToString.Include
    private String email;

    @Column(length = 64, nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false, length = 45)
    @ToString.Include
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    @ToString.Include
    private String lastName;

    @Column(length = 64)
    private String photos;

    private boolean enabled;

    @ManyToMany
    @JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Include
    private Set<Role> roles = new HashSet<>();

    public User(String email, String password, String firstName, String lastName) {
	super();
	this.email = email;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
    }

    public void addRole(Role role) {
	this.roles.add(role);
    }

}
