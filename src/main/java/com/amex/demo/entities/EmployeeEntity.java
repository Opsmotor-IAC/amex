package com.amex.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @NotNull(message = "Employee Name is mandatory")
    @Column(name = "EMPLOYEE_NAME")
    private String name;

    @NotNull(message = "Employee email ID is mandatory")
    @Column(name = "EMAIL_ID")
    private String email;
}
