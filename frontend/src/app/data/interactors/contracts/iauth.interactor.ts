import { Injectable } from "@angular/core";
import { LoginEntity } from "../../../domain/entities/login.entity";
import { Observable } from "rxjs";
import { AuthEntity } from "../../../domain/entities/auth.entity";

@Injectable({ providedIn: 'root' })
export abstract class IAuthInteractor {
    abstract login(login: LoginEntity): Observable<AuthEntity>;
}