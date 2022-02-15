export class LoginResponse {
  token: string;
  error: boolean;
  message: string;
  reason: string;
  status: number;

  constructor(token: string, error: boolean, message: string, reason: string, status: number) {
    this.token = token;
    this.error = error;
    this.message = message;
    this.reason = reason;
    this.status = status;
  }
}
