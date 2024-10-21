package com.ohgiraffers.section02.annotation.subsection03.collection;


import com.ohgiraffers.section02.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pokemonServiceCollection")
public class PokemonService {

    @Autowired
    private List<Pokemon> pokemonList; // 포켓몬 객체를 리스트화

    public void pokemonAttack(){
        for(Pokemon pokemon : pokemonList){
            pokemon.attack();
        }
    }
}
