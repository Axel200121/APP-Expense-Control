import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import dayjs from 'dayjs';
import "dayjs/locale/es"

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  public hora:any;

  ngOnInit(): void {
    this.getActualHour()
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
