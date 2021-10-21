package springdatarest.beans;

import org.springframework.context.annotation.Bean;
import springdatarest.model.User;
import springdatarest.model.Auction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvDataLoader {

    @Bean
    public Auction buildAuctionFromCsv() throws IOException {
        Auction auction = new Auction("1234", 20);
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/users_information.csv"))) {
            String line = null;
            do {
                line = reader.readLine();
                if (line != null) {
                    User user = new User(line);
                    user.setIsRegistered(false);
                    auction.addUser(user);
                }
            } while (line != null);

        }

        return auction;
    }
}
