import { Injectable } from "@angular/core";
import { IAuthInteractor } from "../contracts/iauth.interactor";
import { LoginUseCase } from "../../../domain/usecase/auth-usecase/get-usecase/get.auth.usecase";
import { LoginEntity } from "../../../domain/entities/login.entity";
import { Observable } from "rxjs";
import { AuthEntity } from "../../../domain/entities/auth.entity";

@Injectable({providedIn:'root'})
export class AuthInterActor extends IAuthInteractor {
    constructor(
        private loginUseCase: LoginUseCase,
    ) {
        super();
    }

    public login(login: LoginEntity): Observable<AuthEntity> {
        return this.loginUseCase.execute(login);
    }
}