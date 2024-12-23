import { PokemonProps } from "../../interfaces/pokemonProps";
import { Card, CardMedia, CardContent, Typography, CardActions, Button, Stack } from "@mui/material"
import normalIcon from "./assets/types/normal.png";
import fireIcon from "./assets/types/fire.png";
import waterIcon from "./assets/types/water.png";


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
                            src={`/assets/types/${props.type1.toLowerCase()}.png`}
                            style={{width: 30, height: 15}}
                        />
                        props.type2 && <img
                            key={`${props.name}-${props.type2}`}
                            src={`/assets/types/${props.type2.toLowerCase()}.png`}
                            style={{width: 30, height: 15}}
                        />
                    </Stack>
                </CardContent>
            </Card>
        );
    }
}

export default Pokemon