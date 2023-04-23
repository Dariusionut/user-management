package com.spring.studentmanagement.models;

import jakarta.persistence.*;
import lombok.*;

/**
 * Created at 23.04.2023 by Dan.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "address", schema = "public")
@Entity(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address")
    @SequenceGenerator(name = "seq_address", allocationSize = 1)
    private Long id;

    @Column(name = "country", length = 45, nullable = false)
    private String country;

    @Column(name = "city", length = 45, nullable = false)
    private String city;

    @Column(name = "door_number", length = 10, nullable = false)
    private String doorNumber;

    @Column(name = "additional_details")
    private String additionalDetails;

    @OneToOne(mappedBy = "address")
    private AppUser user;

}