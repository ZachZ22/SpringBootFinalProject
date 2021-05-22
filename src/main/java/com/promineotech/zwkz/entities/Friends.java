package com.promineotech.zwkz.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Friends {
    private long friend_id;
    private int user_id;
    private String first_name;
    private String last_name;
}
