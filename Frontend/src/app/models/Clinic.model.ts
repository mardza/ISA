import {User} from './User.model';
import {Room} from './Room.model';

export class Clinic {
    id: number;
    name: string;
    address: string;
    description: string;
    users: User[];
    rooms: Room[];

    constructor() {
    }

    public static toClinic(clinic: any): Clinic {
        if (!clinic) {
            return undefined;
        }

        const clinicToReturn: Clinic = new Clinic();
        clinicToReturn.id = clinic.id;
        clinicToReturn.name = clinic.name;
        clinicToReturn.address = clinic.address;
        clinicToReturn.description = clinic.description;
        clinicToReturn.users = User.toUserList(clinic.users);
        clinicToReturn.rooms = Room.toRoomList(clinic.rooms);
        return clinicToReturn;
    }

    public static toClinicList(clinicList: any[]): Clinic[] {
        if (!clinicList) {
            return undefined;
        }
        const clinicListToReturn: Clinic[] = [];
        clinicList.forEach(clinic => {
            clinicListToReturn.push(Clinic.toClinic(clinic));
        });
        return clinicListToReturn;
    }
}