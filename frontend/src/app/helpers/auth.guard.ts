import { Injectable } from "@angular/core";
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { AuthenticationService } from "../service/authentication/authentication.service";

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

    constructor(private router : Router, private authenticationService : AuthenticationService) {}
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        const user = this.authenticationService.getUserValue();
    
        if(user) {
            if(route.data["roles"] && route.data["roles"].indexOf(user.role) === -1) {
                this.router.navigate(['/']);
                return false;
            }
            
            return true;
        }

        return false;
    }
    
    
}