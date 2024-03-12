package com.edts.tdp.batch4.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "pram_tgl23")
public class Crud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate;

    @Column(name = "question_number")
    private int questionNumber;

    @Column(name = "deskripsi_soal")
    private String deskripsiSoal;

    @Column
    private String input;

    @Column
    private String result;

    public Crud() {
        setCreatedDate(LocalDateTime.now());
    }
}
