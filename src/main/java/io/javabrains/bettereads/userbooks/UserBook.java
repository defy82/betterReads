package io.javabrains.bettereads.userbooks;

import java.time.LocalDate;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import lombok.Data;

@Data
@Table(value="book_by_user_and_bookid")
public class UserBook {
    

    @PrimaryKey
    private UserBooksPrimaryKey key;

    @Column("reading_status")
    @CassandraType(type = Name.TEXT)
    private String name;

    @Column("started_date")
    @CassandraType(type=Name.DATE)
    private LocalDate startedDate;

    @Column("completed_date")
    @CassandraType(type=Name.DATE)
    private LocalDate completedDate;

    @Column("status")
    @CassandraType(type=Name.TEXT)
    private String status;

    @Column("rating")
    @CassandraType(type=Name.INT)
    private int rating;

}
