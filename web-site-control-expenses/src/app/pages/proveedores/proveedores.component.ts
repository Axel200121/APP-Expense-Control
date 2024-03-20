import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { HeaderComponent } from '../../components/header/header.component';
import { SidebarComponent } from '../../components/sidebar/sidebar.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { RouterLink } from '@angular/router';
import { ProveedoresService } from '../../services/proveedores.service';
import { ProveedoresDto } from '../../interface/proveedores-dto';
import { ApiResponseDto } from '../../interface/api-response-dto';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import swal from 'sweetalert2';

@Component({
  selector: 'app-proveedores',
  standalone: true,
  imports: [FormsModule, HeaderComponent,SidebarComponent,FooterComponent,RouterLink],
  templateUrl: './proveedores.component.html',
  styleUrl: './proveedores.component.css'
})
export class ProveedoresComponent implements OnInit {

  public providersList!:ProveedoresDto[];
  public apiResponseDto!:ApiResponseDto;

  //variables para modal guardar
  @ViewChild("myModalConfig",{static:false})
  myModalConfig!:TemplateRef<any>
  public modalTitle!:string;

  //inicializar dto proveedor para guardar
  public providerDto:ProveedoresDto={
    nombre:''
  }

  constructor(
    private _authService:AuthService,
    private _proveedoresService:ProveedoresService,
    private _modalService:NgbModal
    ){}


  ngOnInit(): void {
    this._authService.validateAuth()  
    this.getAllProviders()
  }

  public getAllProviders(){
    this._proveedoresService.getAllProviders(this._authService.getToken()).subscribe({
      next: data =>{
        console.log(data)
        this.providersList= data.data
      },error: error=>{
        console.log('Error: '+error.message)
      }
    })
  }


  public switchForm(){
    if(this.modalTitle=="Agrega"){
      this.saveProvider()
    }else if (this.modalTitle=="Modifica") {
      
    }
  }

  openSaveModalProvider(){
    this._modalService.open(this.myModalConfig,{size:'lg'});
    this.modalTitle="Agrega"
  }

  private saveProvider(){
    this._proveedoresService.setProvider(this.providerDto,this._authService.getToken()).subscribe({
      next: response =>{
        swal.fire({
          icon:'success',
          title:'OK',
          text:"Proveedor agregado correctamente"
        })
        setInterval(()=>{
          window.location.href ="/providers"
          this.getAllProviders()
        },1500)
        this.getAllProviders()
      },error: error =>{
        console.log("Error--> "+ error.message)
      }
    })
  }

  openEditarModalProvider(providerDto:ProveedoresDto){
    this._modalService.open(this.myModalConfig,{size:'lg'});
    this.modalTitle="Modifica"
  }


}
