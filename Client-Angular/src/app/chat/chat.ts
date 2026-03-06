import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import {environment} from '../../environments/environment.development';

interface ChatResponse {
  content: string;
  type: string;
  format: string;
}

interface Filter {
  period: string;
  category: string;
}

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './chat.html',
  styleUrl: './chat.css'
})
export class Chat {
  userMessage: string = '';
  messages: Array<{content: string, isUser: boolean}> = [];
  isLoading: boolean = false;
  quickSuggestions = [
    "Top 5 plats les plus vendus",
    "Ventes du mois dernier",
    "Taux de gaspillage",
    "Commandes en attente"
  ];

  filters: Filter = {
    period: 'week',
    category: 'all'
  };

  periods = [
    { value: 'day', label: 'Aujourd\'hui' },
    { value: 'week', label: 'Cette semaine' },
    { value: 'month', label: 'Ce mois' },
    { value: 'year', label: 'Cette année' }
  ];

  categories = [
    { value: 'all', label: 'Tous' },
    { value: 'plats', label: 'Plats' },
    { value: 'boissons', label: 'Boissons' },
    { value: 'desserts', label: 'Desserts' }
  ];

  constructor(private http: HttpClient) {}

  sendMessage(message?: string) {
    const messageToSend = message || this.userMessage.trim();
    if (!messageToSend) return;

    // Ajouter le message de l'utilisateur
    this.messages.push({ content: messageToSend, isUser: true });
    this.isLoading = true;

    // Préparer les données avec les filtres
    const payload = {
      message: messageToSend,
      filters: this.filters
    };

    // Envoyer la requête au serveur
    this.http.post<ChatResponse>(`${environment.clientHost}/chat/send`, payload)
      .subscribe({
        next: (response) => {
          this.messages.push({ content: response.content, isUser: false });
          this.isLoading = false;
        },
        error: (error) => {
          console.error('Erreur lors de l\'envoi du message:', error);
          this.messages.push({ content: 'Désolé, une erreur est survenue.', isUser: false });
          this.isLoading = false;
        }
      });

    this.userMessage = '';
  }

  updateFilter(type: 'period' | 'category', value: string) {
    this.filters[type] = value;
  }

  useSuggestion(suggestion: string) {
    this.sendMessage(suggestion);
  }
}
