package com.ftn.isa23.users.domain.enums;

public enum Role {
    SysAdmin(Values.SysAdmin), CompanyAdmin(Values.CompanyAdmin), Customer(Values.Customer);

    Role(String value) {
        if(!this.name().equals(value)) {
            throw new IllegalArgumentException("Incorrect use of role!");
        }
    }

    public static class Values {
        public static final String SysAdmin = "SysAdmin";
        public static final String CompanyAdmin = "CompanyAdmin";
        public static final String Customer = "Customer";
    }
}
