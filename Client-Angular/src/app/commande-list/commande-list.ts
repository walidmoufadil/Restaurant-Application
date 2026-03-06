import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CommandeService, ICommande } from '../commande/commande.service';


@Component({
  selector: 'app-commande-list',
  templateUrl: './commande-list.html',
  styleUrls: ['./commande-list.css'],
  standalone: true,
  imports: [CommonModule],
  providers: [CommandeService]
})
export class CommandeListComponent implements OnInit {
  commandes: ICommande[] = [];
  loading = true;
  error: string | null = null;

  constructor(private commandeService: CommandeService) {}

  ngOnInit(): void {
    this.loadCommandes();
  }

  loadCommandes(): void {
    this.loading = true;
    this.error = null;

    this.commandeService.getAllCommandes().subscribe({
      next: (data) => {
        this.commandes = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des commandes:', err);
        this.error = 'Impossible de charger les commandes. Veuillez réessayer plus tard.';
        this.loading = false;
      }
    });
  }

  updateStatut(commandeId: number, newStatut: string): void {
    this.commandeService.updateStatut(commandeId, newStatut).subscribe({
      next: () => {
        // Mettre à jour le statut localement
        const commande = this.commandes.find(c => c.id === commandeId);
        if (commande) {
          commande.statut = newStatut;
        }
      },
      error: (err) => {
        console.error('Erreur lors de la mise à jour du statut:', err);
        // Recharger les commandes en cas d'erreur
        this.loadCommandes();
      }
    });
  }

  getStatusBadgeClass(statut: string): string {
    switch (statut) {
      case 'EN_ATTENTE':
        return 'bg-yellow-100 text-yellow-800';
      case 'EN_COURS':
        return 'bg-blue-100 text-blue-800';
      case 'PREPAREE':
        return 'bg-green-100 text-green-800';
      case 'LIVREE':
        return 'bg-purple-100 text-purple-800';
      case 'ANNULEE':
        return 'bg-red-100 text-red-800';
      default:
        return 'bg-gray-100 text-gray-800';
    }
  }

  // Méthode pour rafraîchir les commandes
  refreshCommandes(): void {
    this.loadCommandes();
  }
}
