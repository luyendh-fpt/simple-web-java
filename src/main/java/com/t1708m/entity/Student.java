package com.t1708m.entity;

public class Student {

    private String username;
    private String password;
    private String email;
    private String fullName;
    private String address;
    private String phone;
    private boolean vipMember;
    private int role; // 1. user | 2. admin.
    private int status; // 0. deactive | 1. active | -1. deleted

    public Student() {
        this.username = "";
        this.password = "";
        this.email = "";
        this.fullName = "";
        this.address = "";
        this.phone = "";
    }

    public enum Role {

        USER(1), ADMIN(2);

        int value;

        Role(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Role findByValue(int value) {
            for (Role role :
                    Role.values()) {
                if (role.getValue() == value) {
                    return role;
                }
            }
            return null;
        }
    }

    public enum Status {

        ACTIVE(1), DEACTIVE(0), DELETED(-1);

        int value;

        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Status findByValue(int value) {
            for (Status status :
                    Status.values()) {
                if (status.getValue() == value) {
                    return status;
                }
            }
            return null;
        }
    }

    public boolean isVipMember() {
        return vipMember;
    }

    public void setVipMember(boolean vipMember) {
        this.vipMember = vipMember;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Student.Status status) {
        if (status == null) {
            status = Status.DEACTIVE;
        }
        this.status = status.getValue();
    }

    public int getRole() {
        return role;
    }

    public void setRole(Student.Role role) {
        this.role = role.getValue();
    }

}
