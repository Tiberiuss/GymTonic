package com.gym.GymTonic.payload;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BaseResponse<T> {
    private String status;
    private T data;
    private String message;
}

