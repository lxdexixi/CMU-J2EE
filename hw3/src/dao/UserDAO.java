package dao;
import databean.User;
import org.genericdao.*;
import org.genericdao.impl.matcharg.BinaryMatchArg;
import org.genericdao.impl.matcharg.MatchOp;

/**
 * Author : Jialu Chen
 * date: 11/27/2016
 * course: 08672
 */
public class UserDAO extends GenericDAO<User> {


    public UserDAO(ConnectionPool cp) throws DAOException {
        super(User.class, ConstantCopy.TABLE_USER, cp);
    }


    public User read(String email) throws RollbackException {
        User[] users = match(new BinaryMatchArg("emailAddress", MatchOp.EQUALS_IGNORE_CASE, email));
        return users == null || users.length == 0 ? null : users[0];
    }

    @Override
    public User read(Object... primaryKeyValues) throws RollbackException {
        return super.read(primaryKeyValues);
    }

    public User[] getUsers() throws RollbackException {
        return match();
    }

    public void setPassword(int userId, String newPassword) {
        try {
            User user = read(userId);
            user.setPassword(newPassword);
            update(user);
        } catch (RollbackException e) {
            e.printStackTrace();
        }

    }
}
