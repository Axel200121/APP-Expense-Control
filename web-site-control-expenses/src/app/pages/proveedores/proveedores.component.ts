import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { HeaderComponent } from '../../components/header/header.component';
import { SidebarComponent } from '../../components/sidebar/sidebar.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { RouterLink } from '@angular/router';
import { ProveedoresService } from '../../services/proveedores.service';
import { ProveedoresDto } from '../../interface/proveedores-dto';
import { ApiResponseDto } from '../../interface/api-response-dto';

@Component({
  selector: 'app-proveedores',
  standalone: true,
  imports: [HeaderComponent,SidebarComponent,FooterComponent,RouterLink],
  templateUrl: './proveedores.component.html',
  styleUrl: './proveedores.component.css'
})
export class ProveedoresComponent implements OnInit {

  public providersList!:ProveedoresDto[];
  public apiResponseDto!:ApiResponseDto;

  constructor(
    private _authService:AuthService,
    private _proveedoresService:ProveedoresService
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


}
