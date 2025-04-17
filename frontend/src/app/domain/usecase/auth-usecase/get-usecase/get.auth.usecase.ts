import { Injectable } from "@angular/core";
import { UseCase } from "../../../../core/contracts/usecase.contract";
import { LoginEntity } from "../../../entities/login.entity";
import { Observable } from "rxjs";
import { AuthEntity } from "../../../entities/auth.entity";
import { IAuthRepository } from "../../../repository/iauth.repository";


@Injectable({providedIn:'root'})
export class LoginUseCase implements UseCase<LoginEntity, Observable<AuthEntity>> {
    constructor(private iAuthRepository: IAuthRepository) {}

    execute(param: LoginEntity): Observable<AuthEntity> {
        return this.iAuthRepository.login(param);
    }
}