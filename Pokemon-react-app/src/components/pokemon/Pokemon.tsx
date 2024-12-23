import { PokemonProps } from "../../interfaces/pokemonProps";
import { Card, CardMedia, CardContent, Typography, CardActions, Button, Stack } from "@mui/material"

const typeImageMap: { [key: string]: string } = {
    normal: "./assets/types/normal.png",
    fire: "./assets/types/fire.png",
    water: "./assets/types/water.png",
    electric: "./assets/types/electric.png",
    grass: "./assets/types/grass.png",
    ice: "./assets/types/ice.png",
    fighting: "./assets/types/fighting.png",
    poison: "./assets/types/poison.png",
    ground: "./assets/types/ground.png",
    flying: "./assets/types/flying.png",
    psychic: "./assets/types/psychic.png",
    bug: "./assets/types/bug.png",
    rock: "./assets/types/rock.png",
    ghost: "./assets/types/ghost.png",
    dragon: "./assets/types/dragon.png",
  };
function Pokemon(props: PokemonProps){
    if(props.variant === "management"){
        return null
    }

    if(props.variant === "shop"){
        return null
    }

    if(props.variant === "collection"){
        return (
            <Card sx={{objectFit:'contain', maxWidth: 250 }}>
                <CardMedia
                    sx={{ height: 140 }}
                    image={props.imgUrl}
                    title="green iguana"/>
                
                <CardContent>
                    <Typography gutterBottom variant="h5" component="div">
                        {props.name}
                    </Typography>
                    <Stack direction="row" spacing={1} justifyContent="center" sx={{ width: "100%" }}>
                        <img
                            key={`${props.name}-${props.type1}`}
                            src={typeImageMap[props.type1]}
                            style={{width: 30, height: 15}}
                        />,
                        <img
                            key={`${props.name}-${props.type2}`}
                            src={typeImageMap[props.type2]}
                            style={{width: 30, height: 15}}
                        />
                    </Stack>
                </CardContent>
            </Card>
        );
    }
}

export default Pokemon