import {Role} from './Role.model';

export class User {
    id: number;
    email: string;
    password: string;
    firstName: string;
    lastName: string;
    address: string;
    city: string;
    country: string;
    phone: string;
    insuranceNumber: string;
    role: Role;

    constructor() {
    }

    public static toUser(user: any): User {
        if (!user) {
            return undefined;
        }

        const userToReturn: User = new User();
        userToReturn.id = user.id;
        userToReturn.email = user.email;
        userToReturn.password = user.password; // should not be returned from backend
        userToReturn.firstName = user.firstName;
        userToReturn.lastName = user.lastName;
        userToReturn.address = user.address;
        userToReturn.city = user.city;
        userToReturn.country = user.country;
        userToReturn.phone = user.phone;
        userToReturn.insuranceNumber = user.insuranceNumber;
        userToReturn.role = Role.toRole(user.role);
        return userToReturn;
    }

    public static toUserList(userList: any[]): User[] {
        const userListToReturn: User[] = [];
        userList.forEach(user => {
            userListToReturn.push(User.toUser(user));
        });
        return userListToReturn;
    }
}
