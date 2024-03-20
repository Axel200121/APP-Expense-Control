import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { UsuariosComponent } from './pages/usuarios/usuarios.component';
import { ProveedoresComponent } from './pages/proveedores/proveedores.component';
import { GastosFijosComponent } from './pages/gastos-fijos/gastos-fijos.component';
import { GastosPorDiaComponent } from './pages/gastos-por-dia/gastos-por-dia.component';
import { ErrorComponent } from './pages/error/error.component';
import { SinAccesoComponent } from './pages/sin-acceso/sin-acceso.component';

export const routes: Routes = [
    {
        path:"home",
        component:HomeComponent
    },
    {
        path:"",
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
        path:"sin-acceso",
        component:SinAccesoComponent
    },
    {
        path:"**",
        component:ErrorComponent
    }
];
