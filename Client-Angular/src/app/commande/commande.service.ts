import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin, of } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import {environment} from '../../environments/environment.development';

export interface IPlat {
  id: number;
  nom: string;
  description: string;
  prix: number;
  disponibilite: boolean;
  menuId: number;
}

export interface ILigneCommande {
  id: number;
  quantite: number;
  platId: number;
  plat?: IPlat;
}

export interface ICommande {
  id: number;
  telephone: string;
  dateCommande: Date;
  statut: string;
  lignesCommande: ILigneCommande[];
}

@Injectable({
  providedIn: 'root'
})
export class CommandeService {
  //private apiUrl = 'http://localhost:9090/api';
  private apiUrl = `${environment.serverHost}/api`;

  constructor(private http: HttpClient) {}

  getAllCommandes(): Observable<ICommande[]> {
    return this.http.get<ICommande[]>(`${this.apiUrl}/commandes`).pipe(
      switchMap(commandes => {
        console.log('Commandes récupérées:', commandes);
        if (commandes.length === 0) return of([]);

        const commandesWithPlats = commandes.map(commande => {
          const ligneObservables = commande.lignesCommande.map(ligne =>
            this.getPlatById(ligne.platId).pipe(
              map(plat => ({
                ...ligne,
                plat: plat
              }))
            )
          );

          return forkJoin(ligneObservables).pipe(
            map(lignesWithPlats => ({
              ...commande,
              lignesCommande: lignesWithPlats
            }))
          );
        });



        return forkJoin(commandesWithPlats);
      })
    );
  }

  getPlatById(id: number): Observable<IPlat> {
    return this.http.get<IPlat>(`${this.apiUrl}/commandes/plat/${id}`);
  }

  getCommandeById(id: number): Observable<ICommande> {
    return this.http.get<ICommande>(`${this.apiUrl}/commandes/${id}`);
  }

  updateStatut(id: number, statut: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/commandes/updateStatus/${id}?statut=${statut}`, {});
  }
}
