import { HttpInterceptorFn, HttpHeaders } from '@angular/common/http';
import { inject } from '@angular/core';
import { Authentication } from './authentication/authentication';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(Authentication);
  const token = authService.getToken();

  console.log("Interceptor - Request URL:", req.url);

  // Vérifier si c'est une requête d'authentification
  const isAuthRequest = req.url.endsWith('/auth');
  console.log("Interceptor - Is auth request:", isAuthRequest);

  // Ne pas ajouter de token pour les requêtes d'authentification
  if (token && !isAuthRequest) {
    console.log("Interceptor - Adding token to non-auth request");
    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${token}`)
      .set('Accept', 'application/json')
      .set('Content-Type', 'application/json');

    const modifiedReq = req.clone({
      headers: headers
    });

    console.log('Interceptor - Modified request:', {
      url: modifiedReq.url,
      headers: modifiedReq.headers.keys(),
      authHeader: modifiedReq.headers.get('Authorization')
    });

    return next(modifiedReq);
  }

  // Pour les requêtes d'authentification, on laisse passer sans modification
  if (isAuthRequest) {
    console.log('Interceptor - Auth request, passing through without token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/x-www-form-urlencoded');

    const modifiedReq = req.clone({
      headers: headers
    });
    return next(modifiedReq);
  }

  console.log('Interceptor - No token available, passing through as is');
  return next(req);
};
