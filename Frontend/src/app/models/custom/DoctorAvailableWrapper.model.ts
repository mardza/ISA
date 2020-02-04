import {User} from '../User.model';
import {Period} from '../Period.model';

export class DoctorAvailableWrapper {
    doctor: User;
    periodList: Period[];
    appointmentTypeDuration: number;

    constructor() {
    }

    public getAllTimes(): number[] {
        const times: number[] = [];
        this.periodList.forEach(period => {
            for(let t = period.start; t + this.appointmentTypeDuration <= period.end; t += 900000){
                times.push(t);
            }
        });
        return times;
    }

    public static toDoctorAvailableWrapper(doctorAvailableWrapper: any): DoctorAvailableWrapper {
        if(!doctorAvailableWrapper) {
            return undefined;
        }

        const doctorAvailableWrapperToReturn: DoctorAvailableWrapper = new DoctorAvailableWrapper();
        if(doctorAvailableWrapper.doctor){
            doctorAvailableWrapperToReturn.doctor = User.toUser(doctorAvailableWrapper.doctor);
        }
        if(doctorAvailableWrapper.periodList) {
            doctorAvailableWrapperToReturn.periodList = Period.toPeriodList(doctorAvailableWrapper.periodList);
        }
        doctorAvailableWrapperToReturn.appointmentTypeDuration = doctorAvailableWrapper.appointmentTypeDuration;
        return doctorAvailableWrapperToReturn;
    }

    public static toDoctorAvailableWrapperList(doctorAvailableWrapperList: any[]): DoctorAvailableWrapper[] {
        if(!doctorAvailableWrapperList){
            return undefined;
        }
        const doctorAvailableWrapperListToReturn: DoctorAvailableWrapper[] = [];
        doctorAvailableWrapperList.forEach(doctorAvailableWrapper => {
            doctorAvailableWrapperListToReturn.push(DoctorAvailableWrapper.toDoctorAvailableWrapper(doctorAvailableWrapper));
        });
        return doctorAvailableWrapperListToReturn;
    }
}
