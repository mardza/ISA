import {Clinic} from './Clinic.model';

export class Room {
    id: number;
    number: string;
    name: string;
    clinic: Clinic;

    constructor() {
    }

    public static toRoom(room: any): Room {
        if (!room) {
            return undefined;
        }

        const roomToReturn: Room = new Room();
        roomToReturn.id = room.id;
        roomToReturn.number = room.number;
        roomToReturn.name = room.name;
        roomToReturn.clinic = Clinic.toClinic(room.clinic);
        return roomToReturn;
    }

    public static toRoomList(roomList: any[]): Room[] {
        if (!roomList) {
            return undefined;
        }
        const roomListToReturn: Room[] = [];
        roomList.forEach(room => {
            roomListToReturn.push(Room.toRoom(room));
        });
        return roomListToReturn;
    }
}
