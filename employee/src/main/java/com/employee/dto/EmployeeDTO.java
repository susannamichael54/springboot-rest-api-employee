package com.employee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "first_name cannot be blank")
    @NotNull(message = "first_name cannot be null")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "last_name cannot be blank")
    @NotNull(message = "last_name cannot be null")
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank(message = "role cannot be blank")
    @NotNull(message = "role cannot be null")
    private String role;

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof EmployeeDTO)) {
            return false;
        }
        return Objects.equals(this.firstName, ((EmployeeDTO) o).firstName)
                && Objects.equals(this.lastName, ((EmployeeDTO) o).lastName)
                && Objects.equals(this.role, ((EmployeeDTO) o).role)
                && Objects.equals(this.id, ((EmployeeDTO) o).id);
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
