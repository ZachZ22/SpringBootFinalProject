package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {
    @JsonIgnore
    private int transaction_id;

    //one user can have many transactions
    @ManyToOne (fetch = FetchType.LAZY, mappedBy = "user_id");
    private int user_id;
    private Date date_time;
    private int amount;

}
