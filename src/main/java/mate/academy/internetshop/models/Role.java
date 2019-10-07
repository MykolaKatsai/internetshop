package mate.academy.internetshop.models;

public class Role {
    private final Long roleId;
    private RoleName roleName;

    public Role() {
        roleId = 0L;
    }

    public Role(Long roleId) {
        this.roleId = roleId;
    }

    private Role(RoleName roleName) {
        roleId = 0L;
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName.toString();
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
