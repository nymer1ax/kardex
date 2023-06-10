package co.com.java.api.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ResponseRequest {
    private String responseDescription;
    private String resultCode;
    private String date;
    public Object result;
}
