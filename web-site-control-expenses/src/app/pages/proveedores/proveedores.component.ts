import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-proveedores',
  standalone: true,
  imports: [],
  templateUrl: './proveedores.component.html',
  styleUrl: './proveedores.component.css'
})
export class ProveedoresComponent implements OnInit {

  constructor(private _authService:AuthService){}


  ngOnInit(): void {
    this._authService.validateAuth()  
  }


}
