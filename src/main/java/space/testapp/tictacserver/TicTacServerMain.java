package space.testapp.tictacserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("space.testapp.tictacserver.dao.mapper")
public class TicTacServerMain {
    public static void main(String[] args) {
        SpringApplication.run(TicTacServerMain.class, args);
    }
}
