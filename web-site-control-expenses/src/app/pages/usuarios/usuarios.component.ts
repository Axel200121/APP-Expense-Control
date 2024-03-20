import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../../components/header/header.component';
import { SidebarComponent } from '../../components/sidebar/sidebar.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { UsuariosService } from '../../services/usuarios.service';
import { UserDto, UserListDto } from '../../interface/user-dto';
import { ApiResponseDto } from '../../interface/api-response-dto';
import { RouterLink } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import swal from 'sweetalert2';
import dayjs from 'dayjs';



@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [RouterLink,FormsModule,HeaderComponent,SidebarComponent,FooterComponent],
  templateUrl: './usuarios.component.html',
  styleUrl: './usuarios.component.css'
})
export class UsuariosComponent implements OnInit {
  
 // public userDtoList!:UserDto[];
  public userDtoList!:UserListDto[]
  public apiResponseDto!:ApiResponseDto;
  
  // Variables para el modal guardar
  @ViewChild("myModalConfig",{static:false})
  myModalConfig!:TemplateRef<any>
  public modalTitle!:string

  public userDto:UserDto={
    nombre:'',
    correo:'',
    password:''
  }

  constructor(
    private _authService:AuthService, 
    private _userService:UsuariosService,
    private _modalService:NgbModal
    ){}


  ngOnInit(): void {
    this._authService.validateAuth()
    if(this._authService.getPerfil() != 'Administrador'){
      window.location.href="/sin-acceso"
    } 
    this.getAllUsers()
  }

  private getAllUsers(){
    this._userService.getAllUsers(this._authService.getToken()).subscribe({
      next: response =>{
        this.apiResponseDto = response;
        this.userDtoList = this.apiResponseDto.data
      },
      error: error =>{
        console.log("Error---> "+error.messsage)
      }
    })
  }

  public switchForm(){
    if (this.modalTitle == "Agrega") {
      this.saveUser()
    }else if (this.modalTitle == "Modifica") {
      
    }
  }

  openSaveModalUser(){
    this._modalService.open(this.myModalConfig,{size:'lg'})
    this.modalTitle = "Agrega"
    this.userDto={
      nombre:'',
      correo:'',
      password:''
    }
  }

  private saveUser(){
    this._userService.ssaveRegister(this.userDto,this._authService.getToken()).subscribe({
      next: response =>{
        swal.fire({
          icon:'success',
          title:'OK',
          text:"Usuario agregado correctamente"
        })
        setInterval(()=>{
          window.location.href ="/users"
          this.getAllUsers()
        },1500)
      },
      error: error =>{
        console.log("Error--> "+ error.message)
      }
    })
  }

  
  openEditarModalUser(userEditDto:UserDto){
    this._modalService.open(this.myModalConfig,{size:'lg'});
    this.modalTitle="Modifica"
    this.userDto={
      nombre: userEditDto.nombre ,
      correo:userEditDto.correo,
      password:''
    }
  }
  
  
  public getFormatDate(date:string){
    dayjs.locale('es')
    return dayjs(date).format("dddd")+" "+dayjs(date).format("DD")+" de "+dayjs(date).format("MMMM")+
    " de "+dayjs(date).format("YYYY")
  }




}
