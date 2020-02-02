export class Period {
    start: number;
    end: number;

    constructor() {
    }

    public static toPeriod(period: any): Period {
        if(!period) {
            return undefined;
        }

        const periodToReturn: Period = new Period();
        periodToReturn.start = period.start;
        periodToReturn.end = period.end;
        return periodToReturn;
    }

    public static toPeriodList(periodList: any[]): Period[] {
        if(!periodList) {
            return undefined;
        }
        const periodListToReturn: Period[] = [];
        periodList.forEach(period => {
            periodListToReturn.push(Period.toPeriod(period));
        });
        return periodListToReturn;
    }
}
