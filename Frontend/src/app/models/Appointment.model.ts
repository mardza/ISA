import {AppointmentType} from './AppointmentType.model';
import {Room} from './Room.model';
import {User} from './User.model';

export class Appointment {
    id: number;
    time: Date;
    duration: number;
    type: AppointmentType;
    room: Room;
    doctor: User;


    constructor() {
    }

    public static toAppointment(appointment: any): Appointment {
        if(!appointment) {
            return undefined;
        }

        const appointmentToReturn: Appointment = new Appointment();
        appointmentToReturn.id = appointment.id;
        appointmentToReturn.time = appointment.time;
        appointmentToReturn.duration = appointment.duration;
        appointmentToReturn.type = AppointmentType.toAppointmentType(appointment.type);
        appointmentToReturn.room = Room.toRoom(appointment.room);
        appointmentToReturn.doctor = User.toUser(appointment.doctor);
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
