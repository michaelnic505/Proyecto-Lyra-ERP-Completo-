
package com.simplecore.erp.shared.models.users;

import java.io.Serializable;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */

/**
 * Represents a system user.
 * Representa un usuario del sistema.
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private String department;
    private String role;

    // Constructor
    public User(int id, String username, String password, String firstName, String lastName, String email, String position, String department, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;
        this.department = department;
        this.role = role;
    }

    // Getters
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPosition() { return position; }
    public String getDepartment() { return department; }
    public String getRole() { return role; }
}
