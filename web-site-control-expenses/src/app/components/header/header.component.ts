import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import dayjs from 'dayjs';
import "dayjs/locale/es"
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  public hora:any;
  public name!:string;
  public perfil!:string
  constructor(private _authService:AuthService){}

  ngOnInit(): void {
    this.getActualHour()
    this.name =this._authService.getName()
    this.perfil = this._authService.getPerfil()
  }

  public getActualDate(){
    dayjs.locale('es')
    let date = new Date()
    return dayjs(date).format("dddd")+" "+dayjs(date).format("DD")+" de "+dayjs(date).format("MMMM")+
    " de "+dayjs(date).format("YYYY")
  }

  public getActualHour(){
    this.hora= new Date()
    setInterval(()=>{
      this.hora= new Date()
    },1000)
  }

}
