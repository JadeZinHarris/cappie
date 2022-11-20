package com.devmountain.cappie.entities;

import com.devmountain.cappie.dtos.ChampionsDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = "champions")
@Data
@AllArgsConstructor



public class Champions {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(columnDefinition = "text")
        private String body;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public Champions() {
        }

        public Champions(Long id, String body) {
            this.id = id;
            this.body = body;
        }
        @ManyToOne
        @JsonBackReference
        private User user;

        public Champions(ChampionsDto championsDto) {
            if (championsDto.getBody() !=null){
                this.body = championsDto.getBody();
            }
        }

    }


