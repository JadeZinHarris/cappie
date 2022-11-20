package com.devmountain.cappie.services;

import com.devmountain.cappie.dtos.ChampionsDto;
import com.devmountain.cappie.entities.User;
import com.devmountain.cappie.entities.Champions;
import com.devmountain.cappie.repositories.UserRepository;
import com.devmountain.cappie.repositories.championsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class championsServiceImpl implements championsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private championsRepository championRepository;

    @Override
    @Transactional
    public void addchampions(ChampionsDto championsDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Champions champion = new Champions(championsDto);
        userOptional.ifPresent(champion::setUser);
        championRepository.saveAndFlush(champion);
    }

    @Override
    public void deletechampionsById(Long championsId) {

    }

    @Override
    @Transactional
    public void deletechampionById(Long championsId) {
        Optional<Champions> championsOptional = championRepository.findById(championsId);
        championsOptional.ifPresent(champions -> championRepository.delete(champions));
    }
    @Override
    @Transactional
    public void updatechampionsById(ChampionsDto championsDto) {
        Optional<Champions> championsOptional = championRepository.findById(championsDto.getId());
        championsOptional.ifPresent(champions -> {
            champions.setBody(championsDto.getBody());
            championRepository.saveAndFlush(champions);
        });

    }
    @Override
    public List<ChampionsDto> getAllchampionsByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            List<Champions> championsList =championRepository.findAllByUserEquals(userOptional.get());
            return championsList.stream().map(ChampionsDto::new).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
   @Override
   public Optional<ChampionsDto> getchampionsById(Long championsId) {
        Optional<Champions> championsOptional = championRepository.findById(championsId);
       return championsOptional.map(ChampionsDto::new);
   }

}
