package forest.rice.field.k.barcodexxx.entity;

public class PokemonReGet extends Pokemon {

    public static PokemonReGet reGet(Pokemon pokemon) {
        PokemonReGet pokemonReGet = new PokemonReGet();

        pokemonReGet.setCaptorId(pokemon.getCaptorId());
        pokemonReGet.setName(pokemon.getName());
        pokemonReGet.setImageUrl(pokemon.getImageUrl());
        pokemonReGet.setNo(pokemon.getNo());

        return pokemonReGet;
    }
}
