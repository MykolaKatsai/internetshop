package mate.academy.internetshop.models;

public class Role {
    private static Long idGenerator = 0L;
    private Long roleId;
    private RoleName roleName;

    public Role() {
        roleId = idGenerator++;
    }

    private Role(RoleName roleName) {
        roleId = idGenerator++;
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = RoleName.valueOf(roleName);
    }

    public static Role of(String roleName) {
        return new Role(RoleName.valueOf(roleName));
    }

    public enum RoleName {
        ADMIN, USER
    }
}
