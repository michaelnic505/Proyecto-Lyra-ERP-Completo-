package com.simplecore.erp.shared.models.dto;

import java.io.Serializable;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int userId;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String position;
    private final String department;
    private final String role;

    public UserDTO(Builder builder) {
        this.userId = builder.userId;
        this.username = builder.username;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.position = builder.position;
        this.department = builder.department;
        this.role = builder.role;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public String getRole() {
        return role;
    }

    public static class Builder {

        private int userId;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String position;
        private String department;
        private String role;

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder position(String position) {
            this.position = position;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }
    }

}
