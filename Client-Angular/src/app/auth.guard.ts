import { Injectable } from '@angular/core';
import { Router, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Authentication } from './authentication/authentication';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard {
  constructor(
    private auth: Authentication,
    private router: Router
  ) {}

  canActivate(): Observable<boolean | UrlTree> {
    return this.auth.isAuthenticated().pipe(
      map(isAuthenticated => {
        if (isAuthenticated) {
          // Vérifier si le token est présent
          const token = this.auth.getToken();
          if (!token) {
            console.log('Token non trouvé, redirection vers la page de connexion');
            return this.router.createUrlTree(['/login']);
          }

          // Vérifier si le token n'est pas expiré (vous pouvez ajouter une logique supplémentaire ici)
          try {
            const tokenData = JSON.parse(atob(token.split('.')[1]));
            const expirationDate = new Date(tokenData.exp * 1000);
            if (expirationDate <= new Date()) {
              console.log('Token expiré, redirection vers la page de connexion');
              this.auth.logout(); // Déconnexion si le token est expiré
              return this.router.createUrlTree(['/login']);
            }
          } catch (error) {
            console.error('Erreur lors de la vérification du token:', error);
            return this.router.createUrlTree(['/login']);
          }

          return true;
        } else {
          console.log('Utilisateur non authentifié, redirection vers la page de connexion');
          return this.router.createUrlTree(['/login']);
        }
      })
    );
  }
}
