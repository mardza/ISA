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
    predefined: boolean;


    constructor() {
    }

    // These getters are for sorting in tables
    get typeName(): string {
        return this.type?this.type.name:'-';
    }
    get clinicName(): string {
        return this.clinic?this.clinic.name:'-';
    }
    get finalPrice(): number|string {
        return this.price?this.price.finalPrice:'-';
    }
    get roomName(): string {
        return this.room?this.room.name:'-';
    }
    get doctorName(): string {
        return this.doctor?this.doctor.fullName:'-';
    }
    get patientName(): string {
        return this.patient?this.patient.fullName:'-';
    }

    public static toAppointment(appointment: any): Appointment {
        if(!appointment) {
            return undefined;
        }

        const appointmentToReturn: Appointment = new Appointment();
        appointmentToReturn.id = appointment.id;
        appointmentToReturn.time = new Date(appointment.time);
        appointmentToReturn.type = AppointmentType.toAppointmentType(appointment.type);
        appointmentToReturn.clinic = Clinic.toClinic(appointment.clinic);
        appointmentToReturn.price = Price.toPrice(appointment.price);
        appointmentToReturn.room = Room.toRoom(appointment.room);
        appointmentToReturn.doctor = User.toUser(appointment.doctor);
        appointmentToReturn.patient = User.toUser(appointment.patient);
        appointmentToReturn.approved = appointment.approved;
        appointmentToReturn.predefined = appointment.predefined;
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
