import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { HeroComponent } from './heroes/hero/hero.component';
import { ListComponent } from './heroes/list/list.component';

@NgModule({
  // Componentes
  declarations: [
    HeroComponent,
    ListComponent
  ],
  // Cosas publicas
  exports: [
    ListComponent
  ],
  // van Modulos
  imports: [
    CommonModule
  ]
})
export class ComponentsModule {}
