import { EstadoDto } from "./estado-dto"
import { PerfilDto } from "./perfil-dto"


export interface UserDto {
    nombre:string
    correo:string
    password:string
}

export interface UserListDto {
    id:string
    nombre:string
    correo:string
    password:string
    token:string
    fecha:string
    perfilDto:PerfilDto
    estadosDto:EstadoDto
}


