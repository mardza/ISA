import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Room} from '../../models/Room.model';
import {RoomService} from '../http/room.service';
import {Observable} from 'rxjs';

@Injectable({providedIn: 'root'})
export class RoomResolver implements  Resolve<Room> {

    constructor(
        private roomService: RoomService
    ) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Room> | Promise<Room> | Room {
        const id: number = route.params.id;
        return this.roomService.getRoomById(id);
    }
}
