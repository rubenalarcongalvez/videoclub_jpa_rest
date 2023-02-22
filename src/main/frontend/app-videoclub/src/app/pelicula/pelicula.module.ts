import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PeliculaRoutingModule } from './pelicula-routing.module';
import { IndexComponent } from './index/index.component';
import { CreateComponent } from './create/create.component';
import { EditComponent } from './edit/edit.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    IndexComponent,
    CreateComponent,
    EditComponent
  ],
  imports: [
    CommonModule,
    PeliculaRoutingModule,
    ReactiveFormsModule
  ],
  exports: [
    PeliculaRoutingModule,
    IndexComponent,
    CreateComponent,
    EditComponent
  ]
})
export class PeliculaModule { }
