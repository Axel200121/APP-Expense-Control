import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { UsuariosComponent } from './pages/usuarios/usuarios.component';
import { ProveedoresComponent } from './pages/proveedores/proveedores.component';
import { GastosFijosComponent } from './pages/gastos-fijos/gastos-fijos.component';
import { GastosPorDiaComponent } from './pages/gastos-por-dia/gastos-por-dia.component';
import { ErrorComponent } from './pages/error/error.component';

export const routes: Routes = [
    {
        path:"",
        component:HomeComponent
    },
    {
        path:"login",
        component:LoginComponent
    },
    {
        path:"users",
        component:UsuariosComponent
    },
    {
        path:"providers",
        component:ProveedoresComponent
    },
    {
        path:"gastos-fijos",
        component:GastosFijosComponent
    },
    {
        path:"gastos-por-dia",
        component:GastosPorDiaComponent
    },
    {
        path:"**",
        component:ErrorComponent
    }
];
