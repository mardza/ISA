import {HttpErrorResponse} from '@angular/common/http';

export class HttpApiError extends HttpErrorResponse {
    statusCode: number;
    statusText: string;
    message: string;
    serverDate: Date;
    data: any;

    constructor(baseError: HttpErrorResponse) {

        super(baseError);

        let httpApiError: any;
        if (typeof baseError.error === 'string') {
            console.log('error is string');
            httpApiError = JSON.parse(baseError.error);
        } else {
            console.log('error is object');
            httpApiError = baseError.error;
        }

        this.statusCode = httpApiError.statusCode;
        this.statusText = httpApiError.statusText;
        this.message = httpApiError.message;
        if (httpApiError.timestamp) {
            this.serverDate = new Date(httpApiError.timestamp);
        }
        this.data = httpApiError.data;
    }
}
