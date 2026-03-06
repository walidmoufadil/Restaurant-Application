import { Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { CommandeListComponent } from './commande-list/commande-list';
import { Chat } from './chat/chat';
import { AuthenticationComponent } from './authentication/authentication';

export const routes: Routes = [
  { path: 'login', component: AuthenticationComponent },
  {
    path: 'commandes',
    component: CommandeListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'chat',
    component: Chat,
    canActivate: [AuthGuard]
  },
  { path: '', redirectTo: '/commandes', pathMatch: 'full' },
  { path: '**', redirectTo: '/commandes' }
];
