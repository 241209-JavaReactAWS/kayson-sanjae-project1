export interface PokemonProps{
    id: number,
    name: string,
    type1: string,
    type2: string,
    imgUrl: string,
    cost: number,
    variant?: "management" | "shop" | "collection",

    owned?: boolean

}

function Pokemon(props: PokemonProps){
    if(props.variant === "management"){
        return null
    }

    if(props.variant === "shop"){
        return null
    }

    if(props.variant === "collection"){
        return null;
    }
}

export default Pokemon