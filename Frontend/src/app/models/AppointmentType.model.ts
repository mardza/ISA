import {User} from './User.model';
import {Room} from './Room.model';

export class AppointmentType {
    id: number;
    name: string;
    price: number;
    discount: number;

    constructor() {
    }

    public static toAppointmentType(appointmentType: any): AppointmentType {
        if (!appointmentType) {
            return undefined;
        }

        const appointmentTypeToReturn: AppointmentType = new AppointmentType();
        appointmentTypeToReturn.id = appointmentType.id;
        appointmentTypeToReturn.name = appointmentType.name;
        appointmentTypeToReturn.price = appointmentType.price;
        appointmentTypeToReturn.discount = appointmentType.discount;
        return appointmentTypeToReturn;
    }

    public static toAppointmentTypeList(appointmentTypeList: any[]): AppointmentType[] {
        if(!appointmentTypeList){
            return undefined;
        }
        const appointmentTypeListToReturn: AppointmentType[] = [];
        appointmentTypeList.forEach(appointmentType => {
            appointmentTypeListToReturn.push(AppointmentType.toAppointmentType(appointmentType));
        });
        return appointmentTypeListToReturn;
    }
}
