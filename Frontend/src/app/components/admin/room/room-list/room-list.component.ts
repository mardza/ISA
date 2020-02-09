import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {Room} from '../../../../models/Room.model';
import {RoomService} from '../../../../services/http/room.service';

@Component({
    selector: 'app-room-list',
    templateUrl: './room-list.component.html',
    styleUrls: ['./room-list.component.scss']
})
export class RoomListComponent implements OnInit {

    columnsToDisplay: string[];
    dataSource: MatTableDataSource<Room>;
    loading: boolean;


    constructor(
        private roomService: RoomService
    ) {
    }

    ngOnInit() {
        this.columnsToDisplay = ['number', 'name'];
        this.loading = true;
        this.roomService
            .getRooms()
            .subscribe(
                value => {
                    this.dataSource = new MatTableDataSource<Room>();
                    this.dataSource.data = value;
                    this.loading = false;
                },
                error => {
                    this.loading = false;
                }
            );
    }
}
