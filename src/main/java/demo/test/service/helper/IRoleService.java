package demo.test.service.helper;

import demo.test.model.db.Role;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}
