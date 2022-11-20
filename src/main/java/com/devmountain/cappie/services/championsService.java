package com.devmountain.cappie.services;

import com.devmountain.cappie.dtos.ChampionsDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface championsService {
    @Transactional
    void addchampions(ChampionsDto championsDto, Long userId);

    @Transactional
    void deletechampionsById(Long championsId);

    @Transactional
    void deletechampionById(Long championsId);

    @Transactional
    void updatechampionsById(ChampionsDto championsDto);

    List<ChampionsDto> getAllchampionsByUserId(Long userId);

    Optional<ChampionsDto> getchampionsById(Long championsId);
}
