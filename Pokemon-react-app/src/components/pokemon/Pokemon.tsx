import { PokemonProps } from "../../interfaces/pokemonProps";

function Pokemon(props: PokemonProps){
    if(props.variant === "management"){
        return null
    }

    if(props.variant === "shop"){
        return null
    }

    if(props.variant === "collection"){
        return (
            <>
            <img src={props.imgUrl} className="pokemonCard"/>
            <h5>{props.name}</h5>
            </>
        );
    }
}

export default Pokemon