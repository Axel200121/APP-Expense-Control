import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-gastos-por-dia',
  standalone: true,
  imports: [],
  templateUrl: './gastos-por-dia.component.html',
  styleUrl: './gastos-por-dia.component.css'
})
export class GastosPorDiaComponent implements OnInit {

  constructor(private _authService:AuthService){}


  ngOnInit(): void {
    this._authService.validateAuth()  
  }


}
