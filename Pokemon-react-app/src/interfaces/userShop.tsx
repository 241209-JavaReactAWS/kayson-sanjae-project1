import { Pokemon } from "./pokemon";
import { User } from "./user";

export interface userShop{
    userShopId: number,
    user: User,
    pokemon1: Pokemon,
    pokemon2: Pokemon,
    pokemon3: Pokemon,
    pokemon4: Pokemon,
    pokemon5: Pokemon
}