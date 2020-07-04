package com.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    private @Id @GeneratedValue Long id;

    private String firstName;

    private String lastName;

    private String role;

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof  Employee)) {
            return false;
        }
        return Objects.equals(this.id, ((Employee) o).getId())
                && Objects.equals(this.firstName, ((Employee) o).getFirstName())
                && Objects.equals(this.lastName, ((Employee) o).getFirstName())
                && Objects.equals(this.role, ((Employee) o).getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.role);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id
                + ", name='" + this.firstName + '\''
                + ", lastName='" + this.lastName + '\''
                + ", role='" + this.role + '\'' + '}';
    }
}
