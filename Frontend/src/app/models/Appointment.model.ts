import {AppointmentType} from './AppointmentType.model';
import {Room} from './Room.model';
import {User} from './User.model';
import {Price} from './Price.model';
import {Clinic} from './Clinic.model';

export class Appointment {
    id: number;
    time: Date;
    type: AppointmentType;
    clinic: Clinic;
    price: Price;
    room: Room;
    doctor: User;
    patient: User;
    approved: boolean;


    constructor() {
    }

    public static toAppointment(appointment: any): Appointment {
        if(!appointment) {
            return undefined;
        }

        const appointmentToReturn: Appointment = new Appointment();
        appointmentToReturn.id = appointment.id;
        appointmentToReturn.time = appointment.time;
        appointmentToReturn.type = AppointmentType.toAppointmentType(appointment.type);
        appointmentToReturn.clinic = Clinic.toClinic(appointment.clinic);
        appointmentToReturn.price = Price.toPrice(appointment.price);
        appointmentToReturn.room = Room.toRoom(appointment.room);
        appointmentToReturn.doctor = User.toUser(appointment.doctor);
        appointmentToReturn.patient = User.toUser(appointment.patient);
        appointmentToReturn.approved = appointment.approved;
        return appointmentToReturn;
    }

    public static toAppointmentList(appointmentList: any[]): Appointment[] {
        if(!appointmentList) {
            return undefined;
        }
        const appointmentListToReturn: Appointment[] = [];
        appointmentList.forEach(appointment => {
            appointmentListToReturn.push(Appointment.toAppointment(appointment));
        });
        return appointmentListToReturn;
    }
}
