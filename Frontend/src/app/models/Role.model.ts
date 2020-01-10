export class Role {
    id: number;
    name: string;

    constructor() {
    }

    public static toRole(role: any): Role {
        if (!role) {
            return undefined;
        }

        const roleToReturn: Role = new Role();
        roleToReturn.id = role.id;
        roleToReturn.name = role.name;
        return roleToReturn;
    }

    public static toRoleList(roleList: any[]): Role[] {
        const roleListToReturn: Role[] = [];
        roleList.forEach(role => {
            roleListToReturn.push(Role.toRole(role));
        });
        return roleListToReturn;
    }
}
