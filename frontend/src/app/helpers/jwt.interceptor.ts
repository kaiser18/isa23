import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { AuthenticationService } from "../service/authentication/authentication.service";


@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthenticationService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        
        const user = this.authenticationService.getUserValue();
        const isLoggedIn = user && user.token;
        
        if(isLoggedIn) {
            req = req.clone({
                setHeaders : {
                    Authorization : `Bearer ${user.token.accessToken}`
                }
            })
        }
        return next.handle(req);
    }
}