package com.devmountain.cappie.controllers;
import com.devmountain.cappie.services.championsService;
import com.devmountain.cappie.dtos.ChampionsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/champions")
public class ChampionsController {
    @Autowired
    private championsService championsService;
    @GetMapping("/user/{userId}")
    public List<ChampionsDto> getchampionsByUser(@PathVariable Long userId) {
        return championsService.getAllchampionsByUserId(userId);
    }
    @GetMapping("/{championsId}")
    public Optional<ChampionsDto> getchampionsById(@PathVariable Long championId){
        return championsService.getchampionsById(championId);
    }
   @PostMapping("/user/{userId}")
    public void addchampion(@RequestBody ChampionsDto championsDto, @PathVariable Long userId) {
       championsService.addchampions(championsDto,userId);
   }
   @DeleteMapping("/{championId}")
    public void deletechampionById(@PathVariable Long championId) {
        championsService.deletechampionById(championId);
   }
   @PutMapping
    public void updatechampions(@RequestBody ChampionsDto championsDto) {
        championsService.updatechampionsById(championsDto);
   }

}
