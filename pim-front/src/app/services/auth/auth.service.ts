import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {LoginRequestDto} from "../../dto/login.request.dto";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private readonly http: HttpClient) {
  }

  public login(login: LoginRequestDto) {
    return this.http.post('http://localhost:8081/auth/login', login);
  }
}
