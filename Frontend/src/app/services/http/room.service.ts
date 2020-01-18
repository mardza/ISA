import {Injectable} from '@angular/core';
import {globals} from '../../globals';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Room} from '../../models/Room.model';
import * as moment from 'moment';
import {map} from 'rxjs/operators';
import {Clinic} from '../../models/Clinic.model';

@Injectable({providedIn: 'root'})
export class RoomService {

    url = `${globals.backend}/rooms`;
    headerContentTypeJson = new HttpHeaders({'Content-Type': 'application/json'});

    constructor(
        private http: HttpClient
    ) {
    }


    getRooms(query?: { clinicId?: number, number?: string, name?: string, date?: Date }): Observable<Room[]> {
        let params: HttpParams;
        if (query) {
            params = new HttpParams();
            if (query.clinicId) {
                params = params.set('clinicId', query.clinicId.toString());
            }
            if (query.number) {
                params = params.set('number', query.number);
            }
            if (query.name) {
                params = params.set('name', query.name);
            }
            if (query.date) {
                params = params.set('date', moment(query.date).format('yyyy-MM-dd'));
            }
        }
        return this.http
            .get<Room[]>(
                this.url,
                {
                    params: params
                }
            )
            .pipe(
                map(result => Room.toRoomList(result))
            );
    }

    getRoomById(id: number): Observable<Room> {
        return this.http
            .get<Room>(
                this.url + '/' + id
            )
            .pipe(
                map(response => Room.toRoom(response))
            );
    }

    createRoom(room: Room): Observable<Room> {
        return this.http
            .post(
                this.url,
                room,
                {
                    headers: this.headerContentTypeJson
                }
            )
            .pipe(
                map(response => Room.toRoom(response))
            );
    }

    updateRoom(id: number, room: Room): Observable<Room> {
        return this.http
            .put(
                this.url + '/' + id,
                room,
                {
                    headers: this.headerContentTypeJson
                }
            )
            .pipe(
                map(response => Room.toRoom(response))
            );
    }
}
