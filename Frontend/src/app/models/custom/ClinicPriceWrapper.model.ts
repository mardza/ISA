import {Clinic} from '../Clinic.model';
import {Price} from '../Price.model';

export class ClinicPriceWrapper {
    clinic: Clinic;
    price: Price;

    constructor() {
    }

    public static toClinicPriceWrapper(clinicPriceWrapper: any): ClinicPriceWrapper {
        if(!clinicPriceWrapper){
            return undefined;
        }

        const clinicPriceWrapperToReturn: ClinicPriceWrapper = new ClinicPriceWrapper();
        if(clinicPriceWrapper.clinic){
            clinicPriceWrapperToReturn.clinic = Clinic.toClinic(clinicPriceWrapper.clinic);
        }
        if(clinicPriceWrapper.price) {
            clinicPriceWrapperToReturn.price = Price.toPrice(clinicPriceWrapper.price);
        }
        return clinicPriceWrapperToReturn;
    }

    public static toClinicPriceWrapperList(clinicPriceWrapperList: any[]): ClinicPriceWrapper[] {
        if (!clinicPriceWrapperList) {
            return undefined;
        }
        const clinicPriceWrapperListToReturn: ClinicPriceWrapper[] = [];
        clinicPriceWrapperList.forEach(clinicPriceWrapper => {
            clinicPriceWrapperListToReturn.push(ClinicPriceWrapper.toClinicPriceWrapper(clinicPriceWrapper));
        });
        return clinicPriceWrapperListToReturn;
    }
}
