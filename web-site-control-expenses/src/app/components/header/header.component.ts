import { Component } from '@angular/core';
import dayjs from 'dayjs';
import "dayjs/locale/es"

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  public getActualDate(){
    dayjs.locale('es')
    let date = new Date()
    return dayjs(date).format("dddd")+" "+dayjs(date).format("DD")+" de "+dayjs(date).format("MMMM")+
    " de "+dayjs(date).format("YYYY")
  }

}
