package com.devmountain.cappie.dtos;


import com.devmountain.cappie.entities.Champions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChampionsDto implements   Serializable {
        private Long id;
        private String body;
        private UserDto userDto;


        public ChampionsDto(Champions champions) {
            if (champions.getId() !=null){
                this.id = champions.getId();
            }
            if (champions.getBody() != null) {
                this.body = champions.getBody();
            }
        }

}
