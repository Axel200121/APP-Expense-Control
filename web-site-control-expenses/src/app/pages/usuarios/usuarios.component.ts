import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [],
  templateUrl: './usuarios.component.html',
  styleUrl: './usuarios.component.css'
})
export class UsuariosComponent implements OnInit {

  constructor(private _authService:AuthService){}


  ngOnInit(): void {
    this._authService.validateAuth()
    if(this._authService.getPerfil() != 'Administrador'){
      window.location.href="/sin-acceso"
    } 
  }


}
