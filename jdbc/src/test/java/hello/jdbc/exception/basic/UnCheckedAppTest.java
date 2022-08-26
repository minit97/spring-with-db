package hello.jdbc.exception.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

public class UnCheckedAppTest {

    @Test
    void unChecked() {
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(()-> controller.request())
                .isInstanceOf(RuntimeSQLException.class);
    }

    static class Controller {
        Service service = new Service();

        public void request() {
            service.logic();
        }
    }

    static class Service {
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void logic() {
            repository.call();
            networkClient.call();
        }
    }

    static class NetworkClient {
        public void call(){
            throw new RuntimeConnenctionException("연결 실패");
        }
    }

    static class Repository {
        public void call() {
            try {
                runSQL();
            } catch (SQLException e) {
                throw new RuntimeSQLException(e);
            }
        }

        public void runSQL() throws SQLException {
             throw new SQLException("ex");
        }
    }

    static class RuntimeConnenctionException extends  RuntimeException {
        public RuntimeConnenctionException(String message) {
            super(message);
        }
    }

    static class RuntimeSQLException extends RuntimeException {
        public RuntimeSQLException(Throwable cause) {   // 이전 예외를 넣을 수 있다.
            super(cause);
        }
    }
}
