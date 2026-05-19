
package com.simplecore.erp.modules.system.access.models;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class User {
    private final int id;
    private final String username;
    private final Role role;

    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.role = builder.role;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public Role getRole() { return role; }

    public static class Builder {
        private int id;
        private String username;
        private Role role;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
