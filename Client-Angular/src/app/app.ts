import { Component, OnInit } from '@angular/core';
import {CommonModule} from '@angular/common';
import { RouterLink, RouterLinkActive, RouterOutlet, Router } from '@angular/router';
import { Authentication } from './authentication/authentication';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    RouterLink,
    RouterLinkActive,
  ],
  templateUrl: './app.html'
})
export class AppComponent implements OnInit {
  title = 'restaurant-app';
  isAuthenticated = false;

  constructor(private auth: Authentication, private router: Router) {}

  ngOnInit() {
    this.auth.isAuthenticated().subscribe(
      isAuth => this.isAuthenticated = isAuth
    );
  }

  shouldShowHeader(): boolean {
    return this.isAuthenticated && !this.router.url.includes('/login');
  }

  logout(): void {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}
