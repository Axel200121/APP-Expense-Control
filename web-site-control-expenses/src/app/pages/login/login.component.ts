import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import swal from 'sweetalert2';
import { LoginDto, reponseLoginDto } from '../../interface/login-dto';
import { TokenService } from '../../services/token.service';
import { CookieService } from 'ngx-cookie-service';
import { ApiResponseDto } from '../../interface/api-response-dto';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  user:any;
  public loginDto:LoginDto={
    correo:'',
    password:''
  }

  public apiResponseDto!:ApiResponseDto;
  public responseLoginDto!:reponseLoginDto;

  constructor(private _tokenService:TokenService, private _cookieService:CookieService){}


  ngOnInit(): void {
  }


  private validationInputs(){
    if(this.loginDto.correo == ''){
      swal.fire({
        icon:'error',
        timer:2000,
        title:'Ups',
        text:'El campo correo es obligatorio'
      })
      return false
    }else if (this.loginDto.password == '') {
      swal.fire({
        icon:'error',
        timer:2000,
        title:'Ups',
        text:'El campo contraseña es obligatorio'
      })
      return false
    }
    return true
  }

  setLogin(){
    if(this.validationInputs()){
      this._tokenService.login(this.loginDto).subscribe({
      
        next: data => {
          this.responseLoginDto = data.data
          this._cookieService.set('token_expenses_control',this.responseLoginDto.token,1)
          this._cookieService.set('name_expenses_control',this.responseLoginDto.nombre,1)
          this._cookieService.set('idUser_expenses_control',this.responseLoginDto.id,1)
          this._cookieService.set('idPerfil_expenses_control',this.responseLoginDto.perfil_id,1)
          this._cookieService.set('perfil_expenses_control',this.responseLoginDto.perfil,1)
          window.location.href="/home"

        },error(error){
          swal.fire({
            icon:'error',
            timer:2000,
            title:'Ups',
            text:'Las credenciales ingresadas no son validas'
          })
          console.log(error)
          //window.location.href='/'
        }
      })
    }
  }

}
