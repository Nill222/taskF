import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.entity.UserRole;
import com.project.task.tasf.model.mapper.impl.UserRowMapper;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserMapperTest {

    private final UserRowMapper mapper = new UserRowMapper();

    @Test
    void shouldMapResultSetToUser() throws Exception {
        ResultSet rs = mock(ResultSet.class);

        when(rs.getString("name")).thenReturn("Egor");
        when(rs.getString("password_hash")).thenReturn("hash123");
        when(rs.getString("role")).thenReturn("CLIENT");

        User user = mapper.to(rs);

        assertEquals("Egor", user.getName());
        assertEquals("hash123", user.getPasswordHash());
        assertEquals(UserRole.CLIENT, user.getRole());
    }

    @Test
    void shouldMapUserToPreparedStatement() throws Exception {
        PreparedStatement ps = mock(PreparedStatement.class);

        User user = new User("Egor", "hash123", UserRole.CLIENT);

        mapper.mapForInsert(user, ps);

        verify(ps).setString(1, "Egor");
        verify(ps).setString(2, "hash123");
        verify(ps).setString(3, "CLIENT");
    }

}

