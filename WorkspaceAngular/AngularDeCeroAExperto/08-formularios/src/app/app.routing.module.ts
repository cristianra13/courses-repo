import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { TemplateComponent } from './pages/template/template.component';
import { ReactiveComponent } from './pages/reactive/reactive.component';

const APP_ROUTES: Routes = [
  { path: 'template', component: TemplateComponent },
  { path: 'reactivos', component: ReactiveComponent },
  { path: '**', pathMatch: 'full', redirectTo: 'reactivos' }
];

@NgModule({
  imports: [RouterModule.forRoot(APP_ROUTES)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
