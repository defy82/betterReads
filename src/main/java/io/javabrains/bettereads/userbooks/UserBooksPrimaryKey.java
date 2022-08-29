package io.javabrains.bettereads.userbooks;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@PrimaryKeyClass
public class UserBooksPrimaryKey {

    public UserBooksPrimaryKey() {
	}

	@PrimaryKeyColumn(name="user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String userId;
    
    @PrimaryKeyColumn(name="book_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String bookId;
}
