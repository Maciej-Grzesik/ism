import { Injectable } from "@angular/core";
import { IAuthRepository } from "../../../../domain/repository/iauth.repository";
import { Observable } from "rxjs";
import { AuthEntity } from "../../../../domain/entities/auth.entity";
import { LoginEntity } from "../../../../domain/entities/login.entity";
import { HttpClient } from "@angular/common/http";

@Injectable({ providedIn: 'root'})
export class AuthRepository implements IAuthRepository {
    public baseUrl = "";

    constructor(private http: HttpClient) {}

    login(login: LoginEntity): Observable<AuthEntity> {
        return this.http.post<AuthEntity>(`${this.baseUrl}`, login);
    }
}