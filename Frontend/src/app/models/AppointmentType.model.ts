export class AppointmentType {
    id: number;
    name: string;
    duration: number;

    constructor() {
    }

    public static toAppointmentType(appointmentType: any): AppointmentType {
        if (!appointmentType) {
            return undefined;
        }

        const appointmentTypeToReturn: AppointmentType = new AppointmentType();
        appointmentTypeToReturn.id = appointmentType.id;
        appointmentTypeToReturn.name = appointmentType.name;
        appointmentTypeToReturn.duration = appointmentType.duration;
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
