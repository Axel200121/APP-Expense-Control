import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-gastos-fijos',
  standalone: true,
  imports: [],
  templateUrl: './gastos-fijos.component.html',
  styleUrl: './gastos-fijos.component.css'
})
export class GastosFijosComponent implements OnInit {

  constructor(private _authService:AuthService){}


  ngOnInit(): void {
    this._authService.validateAuth()  
  }


}
