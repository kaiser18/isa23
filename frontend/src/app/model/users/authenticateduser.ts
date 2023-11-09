import { Role } from "./role";
import { Token } from "./token";

export class AuthenticatedUser {
    id: string;
    role: Role;
    username: string;
    token: Token;

    constructor(id: string, role: Role, username: string, token: Token) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.token = token;
    }
}