import { Injectable } from "@angular/core";
import { LoginEntity } from "../entities/login.entity";
import { Observable } from "rxjs";
import { AuthEntity } from "../entities/auth.entity";

@Injectable({ providedIn: 'root'})
export abstract class IAuthRepository {
    abstract login(login: LoginEntity): Observable<AuthEntity>;
}