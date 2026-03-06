import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CommandeService, ICommande } from './commande.service';

@Component({
  selector: 'app-commande',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './commande.html',
  providers: [CommandeService]
})
export class CommandeComponent implements OnInit {
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
    console.log('Chargement des commandes...');

    this.commandeService.getAllCommandes().subscribe({
      next: (data: ICommande[]) => {
        console.log('Commandes reçues:', data);
        this.commandes = data;
        this.loading = false;
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des commandes:', error);
        this.error = 'Impossible de charger les commandes';
        this.loading = false;
      }
    });
  }

  updateStatut(commandeId: number, event: any): void {
    const newStatut = event.target.value;
    this.commandeService.updateStatut(commandeId, newStatut).subscribe({
      next: () => {
        const commande = this.commandes.find(c => c.id === commandeId);
        if (commande) {
          commande.statut = newStatut;
        }
      },
      error: (err) => {
        console.error('Erreur lors de la mise à jour du statut:', err);
        this.loadCommandes();
      }
    });
  }

  getStatusColor(statut: string): string {
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
}
