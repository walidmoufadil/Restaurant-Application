import { Component, Injectable } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class Authentication {
  private apiUrl = environment.serverHost;
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  private tokenKey = 'auth_token';

  constructor(private http: HttpClient, private router: Router) {
    this.isAuthenticatedSubject.next(!!this.getToken());
  }

  login(username: string, password: string): Observable<any> {
    const body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);

    return this.http.post<any>(`${this.apiUrl}/auth`, body.toString(), {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }).pipe(
      tap(response => {
        console.log('Auth response:', response);
        const token = response['access-token'];
        if (token) {
          localStorage.setItem(this.tokenKey, token);
          this.isAuthenticatedSubject.next(true);
        }
      })
    );
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
    this.isAuthenticatedSubject.next(false);
    this.router.navigate(['/login']);
  }

  getToken(): string | null {
    const token = localStorage.getItem(this.tokenKey);
    return token;
  }

  isAuthenticated(): Observable<boolean> {
    return this.isAuthenticatedSubject.asObservable();
  }
}

@Component({
  selector: 'app-authentication',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './authentication.html',
  styleUrls: ['./authentication.css']
})
export class AuthenticationComponent {
  username: string = '';
  password: string = '';
  error: string = '';
  loading: boolean = false;

  constructor(
    private authService: Authentication,
    private router: Router
  ) {}

  onSubmit() {
    this.loading = true;
    this.error = '';

    this.authService.login(this.username, this.password).subscribe({
      next: () => {
        this.router.navigate(['/commandes']);
      },
      error: (err) => {
        this.error = 'Identifiants invalides';
        this.loading = false;
      }
    });
  }
}
