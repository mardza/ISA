import {Component, OnInit} from '@angular/core';
import {Room} from '../../../../models/Room.model';
import {ActivatedRoute, Router} from '@angular/router';
import {RoomService} from '../../../../services/http/room.service';
import {NgForm} from '@angular/forms';

@Component({
    selector: 'app-room',
    templateUrl: './room.component.html',
    styleUrls: ['./room.component.scss']
})
export class RoomComponent implements OnInit {

    room: Room;
    isCreateMode: boolean;
    isEditActive: boolean;
    loading: boolean;


    constructor(
        private route: ActivatedRoute,
        private roomService: RoomService,
        private router: Router
    ) {
    }


    ngOnInit() {
        this.room = this.route.snapshot.data.room;
        if (!this.room) {
            this.isCreateMode = true;
        }
    }

    onEditClick() {
        this.isEditActive = true;
    }

    onConfirmClick(form: NgForm) {
        if (form.valid) {
            this.loading = true;
            if (this.isCreateMode) {
                this.roomService
                    .createRoom(Room.toRoom(form.value))
                    .subscribe(
                        value => {
                            this.router.navigate(['/clinics']);
                            this.loading = false;
                        },
                        error => {
                            this.loading = false;
                        }
                    );
            } else {
                this.roomService
                    .updateRoom(this.room.id, Room.toRoom(form.value))
                    .subscribe(
                        value => {
                            this.isEditActive = false;
                            this.loading = false;
                        },
                        error => {
                            this.loading = false;
                        }
                    );
            }
        }
    }

    onCancelClick(form: NgForm) {
        this.isEditActive = false;
        form.reset(this.room);
    }
}
