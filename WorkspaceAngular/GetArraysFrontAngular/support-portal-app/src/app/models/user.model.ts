export class User {
  public userId: string;
  public firstName: string;
  public lastName: string;
  public username: string;
  public email: string;
  public lastLogInDate: Date;
  public lastLoginDateDisplay: Date;
  public joinDate: Date;
  public profileImageUrl: string;
  public activeUser: boolean;
  public notLockedUser: boolean;
  public role: string;
  public authorities: [];

  constructor() {
    this.userId = '';
    this.firstName = '';
    this.lastName = '';
    this.username = '';
    this.email = '';
    this.lastLogInDate = null;
    this.lastLoginDateDisplay = null;
    this.joinDate = null;
    this.profileImageUrl = null;
    this.role = '';
    this.authorities = [];
  }

}
