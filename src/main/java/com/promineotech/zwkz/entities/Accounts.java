package com.promineotech.zwkz.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Accounts {
    private String account_id;
    private String user_id;
    private int account_number;
    private int balance;


}
