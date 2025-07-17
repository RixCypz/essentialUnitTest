package essential.test.UnitTestPractice.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private int id;
    
    @NotBlank(groups = Create.class, message = "Name must not be blank")
    private String name;
    private List<OrderRequest> orders;

    public interface Create {}
    public interface Update {}
}